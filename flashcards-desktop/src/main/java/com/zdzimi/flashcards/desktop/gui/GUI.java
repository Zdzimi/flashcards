package com.zdzimi.flashcards.desktop.gui;

import com.zdzimi.flashcards.desktop.gui.panels.*;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public static Font MY_FONT = new Font("Serif", Font.PLAIN,16);
    public static Font SMALL_FONT = new Font("Serif",Font.PLAIN, 10);

    public static Color BUTTONS_COLOR = Color.WHITE;
    public static Color BUTTONS_NAV_COLOR = Color.GRAY;
    public static Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    public static Color GOOD = Color.GREEN;
    public static Color BAD = Color.RED;

    private JFrame frame;
    private PanelNavigation panelNavigation;
    private JPanel panelCont;
    private CardLayout cardLayout;
    private PanelInfo panelInfo;
    private PanelCreateFromFile panelCreateFromFile;
    private PanelCreate panelCreate;
    private PanelPlay panelPlay;

    public GUI(PanelNavigation panelNavigation,
               PanelInfo panelInfo,
               PanelCreateFromFile panelCreateFromFile,
               PanelCreate panelCreate,
               PanelPlay panelPlay) {
        frame = new JFrame("Flashcard");
        frame.setLayout(null);

        panelCont = new JPanel();
        cardLayout = new CardLayout();
        panelCont.setLayout(cardLayout);

        this.panelNavigation = panelNavigation;
        this.panelInfo = panelInfo;
        this.panelCreateFromFile = panelCreateFromFile;
        this.panelCreate = panelCreate;
        this.panelPlay = panelPlay;
        createGui();
    }

    private void createGui() {

        panelCont.add(panelInfo,"1");
        panelCont.add(panelCreate, "2");
        panelCont.add(panelCreateFromFile,"3");
        panelCont.add(panelPlay,"4");

        cardLayout.show(panelCont, "1");

        frame.setMinimumSize(new Dimension(800,520));
        panelNavigation.setBounds(0,0,120,520);
        panelCont.setBounds(120,0,680,520);
        frame.add(panelNavigation);
        frame.add(panelCont);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
    }

    public void setPanel(String i) {
        cardLayout.show(panelCont,i);
    }

    public void showWindow() {
        frame.setVisible(true);
    }
}
