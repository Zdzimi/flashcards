package com.zdzimi.flashcards.desktop.gui.panels;

import javax.swing.*;
import java.awt.event.ActionListener;

import static com.zdzimi.flashcards.desktop.gui.GUI.BACKGROUND_COLOR;
import static com.zdzimi.flashcards.desktop.gui.GUI.BUTTONS_NAV_COLOR;

public class PanelNavigation extends JPanel {

    private ActionListener actionListener;

    public PanelNavigation(ActionListener actionListener) {
        this.actionListener = actionListener;
        createPanelNavigation();
    }

    private void createPanelNavigation() {
        this.setLayout(null);
        JButton buttonToPanelInfo = new JButton("Info");
        buttonToPanelInfo.setBounds(10,10,100,30);
        buttonToPanelInfo.setBackground(BUTTONS_NAV_COLOR);
        buttonToPanelInfo.setActionCommand("VIEW-1");
        buttonToPanelInfo.addActionListener(actionListener);
        this.add(buttonToPanelInfo);

        JButton buttonToPanelCreate = new JButton("Create");
        buttonToPanelCreate.setBounds(10,50,100,30);
        buttonToPanelCreate.setBackground(BUTTONS_NAV_COLOR);
        buttonToPanelCreate.setActionCommand("VIEW-2");
        buttonToPanelCreate.addActionListener(actionListener);
        this.add(buttonToPanelCreate);

        JButton buttonToPanelCreateFromFile = new JButton("File");
        buttonToPanelCreateFromFile.setBounds(10,90,100,30);
        buttonToPanelCreateFromFile.setBackground(BUTTONS_NAV_COLOR);
        buttonToPanelCreateFromFile.setActionCommand("VIEW-3");
        buttonToPanelCreateFromFile.addActionListener(actionListener);
        this.add(buttonToPanelCreateFromFile);

        JButton buttonToPanelPlay = new JButton("Play");
        buttonToPanelPlay.setBounds(10,130,100,30);
        buttonToPanelPlay.setBackground(BUTTONS_NAV_COLOR);
        buttonToPanelPlay.setActionCommand("VIEW-4");
        buttonToPanelPlay.addActionListener(actionListener);
        this.add(buttonToPanelPlay);

        this.setBackground(BACKGROUND_COLOR);
    }
}
