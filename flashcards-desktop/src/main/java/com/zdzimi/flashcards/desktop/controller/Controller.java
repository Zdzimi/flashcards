package com.zdzimi.flashcards.desktop.controller;

import com.zdzimi.flashcards.core.model.Category;
import com.zdzimi.flashcards.core.model.Flashcard;
import com.zdzimi.flashcards.core.model.FlashcardBox;
import com.zdzimi.flashcards.data.common.ConnectionUtils;
import com.zdzimi.flashcards.data.dao.CategoryDAO;
import com.zdzimi.flashcards.data.dao.CsvDao;
import com.zdzimi.flashcards.data.dao.FlashcardDAO;
import com.zdzimi.flashcards.data.dto.CategoryDTO;
import com.zdzimi.flashcards.data.dto.FlashcardDTO;
import com.zdzimi.flashcards.data.maper.CategoryMaper;
import com.zdzimi.flashcards.data.maper.FlashcardMaper;
import com.zdzimi.flashcards.desktop.gui.GUI;
import com.zdzimi.flashcards.desktop.gui.panels.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zdzimi.flashcards.desktop.gui.GUI.*;

public class Controller implements ActionListener {

    private PanelNavigation panelNavigation;
    private PanelInfo panelInfo;
    private PanelCreateFromFile panelCreateFromFile;
    private PanelFlashcardList panelFlashcardList;
    private PanelFlashcardbox panelFlashcardbox;
    private CategoryDAO categoryDAO;
    private FlashcardDAO flashcardDAO;
    private PanelPlay panelPlay;
    private PanelCreate panelCreate;
    private GUI gui;
    private CsvDao csvDao;
    private List<Flashcard> flashcardList;
    private List<Category> categoryList;
    private FlashcardBox flashcardBox;

    {
        panelNavigation = new PanelNavigation(this);
        panelInfo = new PanelInfo(this);
        panelCreateFromFile = new PanelCreateFromFile(this);
        panelFlashcardList = new PanelFlashcardList();
        panelFlashcardbox = new PanelFlashcardbox();
        try {
            categoryDAO = new CategoryDAO(ConnectionUtils.getConnection());
            flashcardDAO = new FlashcardDAO(ConnectionUtils.getConnection());
            categoryList = createCategoryList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        panelPlay = new PanelPlay(this, categoryList, panelFlashcardbox);
        panelCreate = new PanelCreate(this, panelFlashcardList, categoryList);
        gui = new GUI(panelNavigation, panelInfo, panelCreateFromFile, panelCreate, panelPlay);
        csvDao = new CsvDao();
        flashcardList = new ArrayList<>();
    }

    public void run() {
        gui.showWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] actionTab = e.getActionCommand().split("-");
        switch (actionTab[0]){
            case "VIEW":
                gui.setPanel(actionTab[1]);
                break;
            case "FILE":
                getFileAndSaveData();
                break;
            case "ADD":
                createFlashcardAndAddToList();
                break;
            case "SAVE":
                saveDataFromFlashcardList();
                break;
            case "CLEAR":
                clearFlashcardList();
                break;
            case "DELETE":
                deleteSelectedCategory();
                break;
            case "START":
                startGame();
                break;
            case "CHECK":
                check();
                break;
        }
    }

    private void check() {
        if (flashcardBox.getCounter() % 2 == 0){
            checkAnswer();
        }else {
            setNextFlashcard();
        }
        flashcardBox.incrementCunter();
    }

    private void setNextFlashcard() {
        Flashcard flashcard = flashcardBox.getRandomFlashcardWithMinimalStatus();
        panelPlay.setGoodAnswersText("");
        panelPlay.clearAnswerTextField();
        panelPlay.setAnswersBackgroundColor(BUTTONS_COLOR);
        if (flashcard == null){
            flashcardBox.setCategoryLastUpdate();
            CategoryDTO categoryDTO = CategoryMaper.mapCategoryToCategoryDTO(flashcardBox.getCurrentCategory());
            List<FlashcardDTO> flashcardDTOList = FlashcardMaper.mapFlashcardListToFlashcardDTOList(flashcardBox.getDone());
            try {
                saveData(categoryDTO, flashcardDTOList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            panelPlay.setQuestionText("The End");
            panelPlay.setButtonText("");
            panelPlay.setButtonCheckOff();
            panelPlay.setTextFieldAnswerOff();
        } else {
            panelPlay.setQuestionText(flashcard.getQuestion());
            panelPlay.setButtonText("Check");
        }
    }

    private void checkAnswer() {
        String answerText = panelPlay.getAnswerText();
        if (flashcardBox.checkAnswer(answerText)){
            panelFlashcardbox.setToUpperFlashcardBoxView(flashcardBox.getCurrentFlashcard());
            flashcardBox.incrementCurrentFlashcardStatus();
            panelPlay.setAnswersBackgroundColor(GOOD);
        } else {
            panelFlashcardbox.setToFirstFlashcardBoxView(flashcardBox.getCurrentFlashcard());
            flashcardBox.resetCurrentFlashcardStatus();
            panelPlay.setAnswersBackgroundColor(BAD);
        }
        panelPlay.setGoodAnswersText(flashcardBox.getCurrentFlashcard().getAnswer());
        flashcardBox.addCurrentFlashcardToDoneList();
        panelPlay.setButtonText("Next");
    }

    private void saveData(CategoryDTO categoryDTO, List<FlashcardDTO> flashcardDTOList) throws SQLException {
        if (categoryDAO.findById(categoryDTO.getCategoryId()) == null){
            CategoryDTO newCategoryDTO = categoryDAO.create(categoryDTO);
            addItemToComboBoxes(CategoryMaper.mapCategoryDTOToCategory(newCategoryDTO));
            for (FlashcardDTO flashcardDTO : flashcardDTOList) {
                flashcardDTO.setCategoryId(newCategoryDTO.getCategoryId());
                flashcardDAO.create(flashcardDTO);
            }
        } else {
            categoryDAO.update(categoryDTO);
            for (FlashcardDTO flashcardDTO : flashcardDTOList) {
                flashcardDAO.update(flashcardDTO);
            }
        }
    }

    private void startGame() {
        Category category = panelPlay.getSelectedCategory();
        if (DateUtils.resume(category)){
            resume(category);
        }else {
            panelPlay.setQuestionText("take a break of five days to improve memory efficiency");
        }

    }

    private void resume(Category category) {
        List<Flashcard> flashcardList = null;
        try {
            List<FlashcardDTO> flashcardDTOList = flashcardDAO.findByCategoryId(category.getCategoryId());
            flashcardList = FlashcardMaper.mapFlashcardDTOListToFlashcardList(flashcardDTOList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flashcardList != null && flashcardList.size() > 0) {
            panelFlashcardbox.showFlashcardBoxes(flashcardList);
            flashcardBox = FlashcardBox.createNewFlashcardBox(flashcardList, category);
            Flashcard flashcard = flashcardBox.getRandomFlashcardWithMinimalStatus();
            panelPlay.setQuestionText(flashcard.getQuestion());
            panelPlay.setButtonCheckOn();
            panelPlay.setButtonText("Check");
            panelPlay.setTextFieldAnswerOn();
        }
    }

    private void deleteSelectedCategory() {
        Category category = panelCreate.getSelectedCategory();
        try {
            flashcardDAO.deleteByCategoryId(category.getCategoryId());
            categoryDAO.delete(CategoryMaper.mapCategoryToCategoryDTO(category));
            removeItemFromComboBoxes(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFlashcardList() {
        flashcardList.clear();
        panelCreate.clearTextCategory();
        panelFlashcardList.clearListView();
    }

    private void saveDataFromFlashcardList() {
        String textCategory = panelCreate.getTextCategory();
        if (!textCategory.equals("")){
            try {
                Category category = createNewOrGetCategory(textCategory);
                setCategoryAndSaveFlashcardList(category.getCategoryId());
                flashcardList.clear();
                panelCreate.clearTextCategory();
                panelFlashcardList.clearListView();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCategoryAndSaveFlashcardList(int categoryId) throws SQLException {
        for (Flashcard flashcard : flashcardList) {
            flashcard.setCategoryId(categoryId);
            flashcardDAO.create(FlashcardMaper.mapFlashcardToFlashcardDTO(flashcard));
        }
    }

    private void createFlashcardAndAddToList() {
        String textQuestion = panelCreate.getTextQuestion();
        String textAnswer = panelCreate.getTextAnswer();
        if (!textAnswer.equals("") && !textQuestion.equals("")){
            flashcardList.add(new Flashcard(textQuestion,textAnswer));
            panelCreate.clearTextQuestionAndAnswer();
            panelFlashcardList.updateListView(flashcardList);
        }
    }

    private void getFileAndSaveData(){
        File file = panelCreateFromFile.loadFile();
        String categoryName = panelCreateFromFile.getCategoryTitle();
        if (file != null && !categoryName.equals("")) {
            try {
                Category category = createNewOrGetCategory(categoryName);
                List<Flashcard> flashcards = csvDao.loadFile(file, category.getCategoryId());
                saveAll(flashcards);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            panelCreateFromFile.clearTextField();
        }
    }

    private void saveAll(List<Flashcard> flashcards) throws SQLException {
        for (Flashcard flashcard : flashcards) {
            flashcardDAO.create(FlashcardMaper.mapFlashcardToFlashcardDTO(flashcard));
        }
    }

    private Category createNewOrGetCategory(String categoryName) throws SQLException {
        CategoryDTO categoryDTOOld = categoryDAO.findByCategoryName(categoryName);
        if (categoryDTOOld == null){
            CategoryDTO categoryDTONew = categoryDAO.create(new CategoryDTO(categoryName));
            Category category = CategoryMaper.mapCategoryDTOToCategory(categoryDTONew);
            addItemToComboBoxes(category);
            return category;
        }
        return CategoryMaper.mapCategoryDTOToCategory(categoryDTOOld);
    }

    private List<Category> createCategoryList() throws SQLException {
        List<CategoryDTO> categoryDTOList = categoryDAO.findAll();
        return CategoryMaper.mapCategoryDTOListToCategoryList(categoryDTOList);
    }

    private void addItemToComboBoxes(Category category) {
        categoryList.add(category);
        panelPlay.updateComboBoxPlay(categoryList);
        panelCreate.updateComboBoxDelete(categoryList);
    }

    private void removeItemFromComboBoxes(Category category) {
        categoryList.remove(category);
        panelPlay.updateComboBoxPlay(categoryList);
        panelCreate.updateComboBoxDelete(categoryList);
    }
}
