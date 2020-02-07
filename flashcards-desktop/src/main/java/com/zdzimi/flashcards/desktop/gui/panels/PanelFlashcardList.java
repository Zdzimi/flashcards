package com.zdzimi.flashcards.desktop.gui.panels;

import com.zdzimi.flashcards.core.model.Flashcard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.zdzimi.flashcards.desktop.gui.GUI.SMALL_FONT;

public class PanelFlashcardList extends JPanel {

    private JLabel[][] flaschcardList;

    public PanelFlashcardList() {
        createPanelFlashcardList();
    }

    public void createPanelFlashcardList(){
        this.setLayout(new GridLayout(15,2,5,5));
        flaschcardList = fillWithLabels();
    }

    private JLabel[][] fillWithLabels() {
        JLabel[][] table = new JLabel[15][2];
        JLabel label;
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                label = new JLabel("", SwingConstants.CENTER);
                table[row][col] = label;
                table[row][col].setFont(SMALL_FONT);
                this.add(label);
            }
        }
        return table;
    }

    public void updateListView(List<Flashcard> list) {
        for (int row = 0; row < flaschcardList.length && row < list.size(); row++) {
            for (int col = 0; col < flaschcardList[row].length; col++) {
                String text = col == 0 ?
                        list.get(list.size() - 1 - row).getQuestion() : list.get(list.size() - 1 - row).getAnswer();
                flaschcardList[row][col].setText(text);
            }
        }
    }

    public void clearListView() {
        for (int row = 0; row < flaschcardList.length; row++) {
            for (int col = 0; col < flaschcardList[row].length; col++) {
                flaschcardList[row][col].setText("");
            }
        }
    }
}
