package com.zdzimi.flashcards.desktop.gui.panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionListener;
import java.io.File;

import static com.zdzimi.flashcards.desktop.gui.GUI.*;

public class PanelCreateFromFile extends JPanel {

    private ActionListener actionListener;
    private JFileChooser fileChooser;
    private JTextField textField;

    public PanelCreateFromFile(ActionListener actionListener) {
        this.actionListener = actionListener;
        createPanelCreateFromFile();
    }

    private void createPanelCreateFromFile() {
        this.setLayout(null);
        this.setBackground(BACKGROUND_COLOR);

        JLabel categoryInfo = new JLabel("Set category name (required):", SwingConstants.CENTER);
        categoryInfo.setFont(MY_FONT);
        categoryInfo.setBounds(140,150,400,30);
        this.add(categoryInfo);

        this.textField = new JTextField();
        textField.setBounds(240,190,200,30);
        textField.setBackground(BUTTONS_COLOR);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(MY_FONT);
        this.add(textField);

        JLabel loadFileInfo = new JLabel("Choose csv file:", SwingConstants.CENTER);
        loadFileInfo.setFont(MY_FONT);
        loadFileInfo.setBounds(140,230, 400,30);
        this.add(loadFileInfo);

        JButton buttonLoadFile = new JButton("Load csv file...");
        buttonLoadFile.setBounds(240,270,200,30);
        buttonLoadFile.setBackground(BUTTONS_COLOR);
        buttonLoadFile.setActionCommand("FILE");
        buttonLoadFile.addActionListener(actionListener);
        this.add(buttonLoadFile);

        fileChooser = new JFileChooser(
                FileSystemView.getFileSystemView()
                        .getParentDirectory(new File("resources/flashcard.csv")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.csv","csv"));
    }

    public File loadFile(){
        fileChooser.showOpenDialog(this);
        return fileChooser.getSelectedFile();
    }

    public String getCategoryTitle() {
        return textField.getText();
    }

    public void clearTextField(){
        textField.setText("");
    }

}
