package com.zdzimi.flashcards.desktop.gui.panels;

import com.zdzimi.flashcards.core.model.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static com.zdzimi.flashcards.desktop.gui.GUI.*;

public class PanelPlay extends JPanel {

    private ActionListener actionListener;
    private JComboBox<Category> comboBoxPlay;
    private JLabel labelQuestion;
    private JLabel labelGoodAnswers;
    private JTextField textFieldAnswer;
    private JButton buttonCheck;
    private PanelFlashcardbox panelFlashcardbox;


    public PanelPlay(ActionListener actionListener, List<Category> categoryList, PanelFlashcardbox panelFlashcardbox) {
        this.actionListener = actionListener;
        this.panelFlashcardbox = panelFlashcardbox;
        createPanelPlay(categoryList);
    }

    private void createPanelPlay(List<Category> categoryList) {
        this.setLayout(null);
        this.setBackground(BACKGROUND_COLOR);

        comboBoxPlay = new JComboBox<>();
        comboBoxPlay.setBounds(10,10,150,30);
        comboBoxPlay.setBackground(BACKGROUND_COLOR);
        setItemsToComboBoxPlay(categoryList);
        this.add(comboBoxPlay);

        JButton buttonStart = new JButton("Start");
        buttonStart.setBounds(10,50,150,30);
        buttonStart.setBackground(BUTTONS_COLOR);
        buttonStart.setActionCommand("START");
        buttonStart.addActionListener(actionListener);
        this.add(buttonStart);

        labelQuestion = new JLabel("Question",SwingConstants.CENTER);
        labelQuestion.setBounds(10,100,660,40);
        labelQuestion.setFont(MY_FONT);
        this.add(labelQuestion);

        labelGoodAnswers = new JLabel("", SwingConstants.CENTER);
        labelGoodAnswers.setBounds(10,140, 660,40);
        labelGoodAnswers.setFont(MY_FONT);
        this.add(labelGoodAnswers);

        textFieldAnswer = new JTextField();
        textFieldAnswer.setBounds(140,190,400,30);
        textFieldAnswer.setBackground(BUTTONS_COLOR);
        textFieldAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldAnswer.setEnabled(false);
        this.add(textFieldAnswer);

        buttonCheck = new JButton("");
        buttonCheck.setBounds(290, 230,100,30);
        buttonCheck.setBackground(BUTTONS_COLOR);
        buttonCheck.setActionCommand("CHECK");
        buttonCheck.addActionListener(actionListener);
        buttonCheck.setEnabled(false);
        this.add(buttonCheck);

        panelFlashcardbox.setBounds(100,300,480,140);
        panelFlashcardbox.setBackground(BUTTONS_COLOR);
        this.add(panelFlashcardbox);
    }

    private void setItemsToComboBoxPlay(List<Category> categoryList) {
        for (Category category : categoryList) {
            comboBoxPlay.addItem(category);
        }
    }

    public void updateComboBoxPlay(List<Category> categoryList) {
        comboBoxPlay.removeAllItems();
        setItemsToComboBoxPlay(categoryList);
    }

    public Category getSelectedCategory() {
        return (Category) comboBoxPlay.getSelectedItem();
    }

    public void setQuestionText(String text){
        labelQuestion.setText(text);
    }

    public String getAnswerText(){
        return textFieldAnswer.getText();
    }

    public void clearAnswerTextField(){
        textFieldAnswer.setText("");
    }

    public void setButtonCheckOn(){
        buttonCheck.setEnabled(true);
    }

    public void setButtonCheckOff(){
        buttonCheck.setEnabled(false);
    }

    public void setTextFieldAnswerOn(){
        textFieldAnswer.setEnabled(true);
    }

    public void setTextFieldAnswerOff(){
        textFieldAnswer.setEnabled(false);
    }

    public void setGoodAnswersText(String text){
        labelGoodAnswers.setText(text);
    }

    public void setAnswersBackgroundColor(Color color){
        textFieldAnswer.setBackground(color);
    }

    public void setButtonText(String text){
        buttonCheck.setText(text);
    }
}
