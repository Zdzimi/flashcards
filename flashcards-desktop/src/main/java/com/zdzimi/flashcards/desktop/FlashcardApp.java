package com.zdzimi.flashcards.desktop;

import com.zdzimi.flashcards.desktop.controller.Controller;

import javax.swing.*;

public class FlashcardApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            Controller controller = new Controller();
            controller.run();
        });
    }
}
