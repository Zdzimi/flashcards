package com.zdzimi.flashcards.desktop.gui.panels;

import com.zdzimi.flashcards.core.model.Flashcard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.zdzimi.flashcards.desktop.gui.GUI.SMALL_FONT;

public class PanelFlashcardbox extends JPanel {

    private JLabel[][] flashBoxes;

    public PanelFlashcardbox() {
        createPanelFlashboxes();
    }

    private void createPanelFlashboxes() {
        this.setLayout(new GridLayout(5,3,10,10));
        flashBoxes = fillWithLabels();
    }

    private JLabel[][] fillWithLabels() {
        JLabel[][] labels = new JLabel[5][3];
        JLabel jLabel;
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                jLabel = new JLabel("", SwingConstants.CENTER);
                labels[i][j] = jLabel;
                labels[i][j].setFont(SMALL_FONT);
                this.add(jLabel);
            }
        }
        return labels;
    }

    public void showFlashcardBoxes(List<Flashcard> flashcardList){
        for (int row = 0; row < flashBoxes.length; row++) {
            for (int col = 0; col < flashBoxes[row].length; col++) {
                if (col % 2 == 0){
                    flashBoxes[row][col].setText("");
                }else {
                    flashBoxes[row][col].setText("BOX NO " + (1 + row));
                }
            }
        }

        for(Flashcard flashcard : flashcardList){
            Integer f = flashcard.getFlashcardStatus();
            String text = flashBoxes[f][0].getText();
            flashBoxes[f][0].setText(text + "O");
        }
    }

    public void setToUpperFlashcardBoxView(Flashcard currentFlashcard) {
        Integer flashcardStatus = currentFlashcard.getFlashcardStatus();
        String text = flashBoxes[flashcardStatus][0].getText();
        flashBoxes[flashcardStatus][0].setText(text.replaceFirst("O",""));

        int x = flashcardStatus < 4 ? flashcardStatus + 1 : flashcardStatus;

        String text1 = flashBoxes[x][2].getText();
        flashBoxes[x][2].setText(text1 + "O");
    }

    public void setToFirstFlashcardBoxView(Flashcard currentFlashcard) {
        Integer flashcardStatus = currentFlashcard.getFlashcardStatus();
        String text = flashBoxes[flashcardStatus][0].getText();
        flashBoxes[flashcardStatus][0].setText(text.replaceFirst("O",""));

        String text1 = flashBoxes[0][2].getText();
        flashBoxes[0][2].setText(text1 + "O");
    }
}
