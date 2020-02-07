package com.zdzimi.flashcards.desktop.gui.panels;

import javax.swing.*;
import java.awt.event.ActionListener;

import static com.zdzimi.flashcards.desktop.gui.GUI.BACKGROUND_COLOR;
import static com.zdzimi.flashcards.desktop.gui.GUI.MY_FONT;

public class PanelInfo extends JPanel {

    private ActionListener actionListener;

    public PanelInfo(ActionListener actionListener) {
        this.actionListener = actionListener;
        createPanelInfo();
    }

    private void createPanelInfo() {
        this.setLayout(null);

        JLabel label = new JLabel("Flashcards based on book: Kotarski Radek \"Włam się do mózgu\"",SwingConstants.CENTER);
        label.setBounds(10,100,660,40);
        label.setFont(MY_FONT);
        this.add(label);

        this.setBackground(BACKGROUND_COLOR);
    }
}
