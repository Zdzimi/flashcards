package com.zdzimi.flashcards.desktop.gui.panels;

import com.zdzimi.flashcards.core.model.Category;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

import static com.zdzimi.flashcards.desktop.gui.GUI.*;

public class PanelCreate extends JPanel{

    private ActionListener actionListener;
    private JTextField textFieldCategory;
    private JTextField textFieldQuestion;
    private JTextField textFieldAnswer;
    private PanelFlashcardList panelFlashcardList;
    private JComboBox<Category> comboBoxDelete;

    public PanelCreate(ActionListener actionListener,
                       PanelFlashcardList panelFlashcardList,
                       List<Category> categoryList) {
        this.actionListener = actionListener;
        this.panelFlashcardList = panelFlashcardList;
        createPanelCreate(categoryList);
    }

    private void createPanelCreate(List<Category> categoryList) {
        this.setLayout(null);
        this.setBackground(BACKGROUND_COLOR);

        panelFlashcardList.setBounds(300,20,330,300);
        panelFlashcardList.setBackground(BACKGROUND_COLOR);
        this.add(panelFlashcardList);

        JButton buttonClear = new JButton("Clear");
        buttonClear.setBounds(415,320,100,25);
        buttonClear.setBackground(BUTTONS_COLOR);
        buttonClear.setActionCommand("CLEAR");
        buttonClear.addActionListener(actionListener);
        this.add(buttonClear);

        JLabel labelQuestion = new JLabel("Question:", SwingConstants.CENTER);
        labelQuestion.setBounds(50,10,200,30);
        labelQuestion.setFont(MY_FONT);
        this.add(labelQuestion);

        this.textFieldQuestion = new JTextField();
        textFieldQuestion.setBounds(50,50,200,30);
        textFieldQuestion.setBackground(BUTTONS_COLOR);
        textFieldQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldQuestion.setFont(MY_FONT);
        this.add(textFieldQuestion);

        JLabel labelAnswer = new JLabel("Answers separated by a comma:", SwingConstants.CENTER);
        labelAnswer.setBounds(50,90,200,30);
        labelAnswer.setFont(MY_FONT);
        this.add(labelAnswer);

        this.textFieldAnswer = new JTextField();
        textFieldAnswer.setBounds(50,130,200,30);
        textFieldAnswer.setBackground(BUTTONS_COLOR);
        textFieldAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldAnswer.setFont(MY_FONT);
        this.add(textFieldAnswer);

        JButton buttonAdd = new JButton("Add");
        buttonAdd.setBounds(50,170,200,30);
        buttonAdd.setBackground(BUTTONS_COLOR);
        buttonAdd.setActionCommand("ADD");
        buttonAdd.addActionListener(actionListener);
        this.add(buttonAdd);

        JLabel labelDelete = new JLabel("Choose category to delete:", SwingConstants.CENTER);
        labelDelete.setBounds(50,320,200,30);
        labelDelete.setFont(MY_FONT);
        this.add(labelDelete);

        comboBoxDelete = new JComboBox<>();
        comboBoxDelete.setBounds(50,360,200,30);
        comboBoxDelete.setBackground(BUTTONS_COLOR);
        setItemsToComboBoxDelete(categoryList);
        this.add(comboBoxDelete);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(50, 400, 200, 30);
        buttonDelete.setBackground(BUTTONS_COLOR);
        buttonDelete.setActionCommand("DELETE");
        buttonDelete.addActionListener(actionListener);
        this.add(buttonDelete);

        JLabel categoryInfo = new JLabel("Set category name (required):", SwingConstants.CENTER);
        categoryInfo.setFont(MY_FONT);
        categoryInfo.setBounds(315,360,300,30);
        this.add(categoryInfo);

        this.textFieldCategory = new JTextField();
        textFieldCategory.setBounds(365, 400,200,30);
        textFieldCategory.setBackground(BUTTONS_COLOR);
        textFieldCategory.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldCategory.setFont(MY_FONT);
        this.add(textFieldCategory);

        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(365,440,200,30);
        buttonSave.setBackground(BUTTONS_COLOR);
        buttonSave.setActionCommand("SAVE");
        buttonSave.addActionListener(actionListener);
        this.add(buttonSave);
    }

    public String getTextQuestion(){
        return textFieldQuestion.getText();
    }

    public String getTextAnswer(){
        return textFieldAnswer.getText();
    }

    public String getTextCategory(){
        return textFieldCategory.getText();
    }

    public void clearTextQuestionAndAnswer(){
        textFieldQuestion.setText("");
        textFieldAnswer.setText("");
    }

    public void clearTextCategory(){
        textFieldCategory.setText("");
    }

    private void setItemsToComboBoxDelete(List<Category> categoryList) {
        for (Category category : categoryList) {
            comboBoxDelete.addItem(category);
        }
    }

    public void updateComboBoxDelete(List<Category> categoryList) {
        comboBoxDelete.removeAllItems();
        setItemsToComboBoxDelete(categoryList);
    }

    public Category getSelectedCategory() {
        return (Category) comboBoxDelete.getSelectedItem();
    }
}
