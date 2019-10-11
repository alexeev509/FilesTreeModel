package com.company.listeners;

import com.company.structures.FileTreeModel;
import com.company.txt.classes.FilesValidation;
import com.company.txt.classes.TxtFinder;
import com.company.view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ButtonOpenDirectoryActionListener implements ActionListener {

    private FilesValidation filesValidation = new FilesValidation();
    private TxtFinder txtFinder = new TxtFinder();
    private MainWindow window;
    private final static String FILE_CHOOSER_APPROVE_BUTTON_TXT = "Открыть файл";
    private final static String MESSAGE_EMPTY_EXTENSION = "You must write extension of file!\nIt couldn't be empty";
    private final static String MESSAGE_EMPTY_TEXT_FOR_SEARCH = "You must write text for search of file!\nIt couldn't be empty";

    public ButtonOpenDirectoryActionListener(MainWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String extensionOfFile = window.getExtensionOfFileTextfield().getText().trim();
        String textForSearch = window.getTextForSearchTextfield().getText().trim();
        JFileChooser fileChooser = window.getFileChooser();
        int ret = fileChooser.showDialog(null, FILE_CHOOSER_APPROVE_BUTTON_TXT);
        if (ret == JFileChooser.APPROVE_OPTION) {
            if ("".equals(extensionOfFile)) {
                window.showMessageBox(MESSAGE_EMPTY_EXTENSION);
            } else if ("".equals(textForSearch)) {
                window.showMessageBox(MESSAGE_EMPTY_TEXT_FOR_SEARCH);
            } else {
                final File file = fileChooser.getCurrentDirectory();

                try {

                    List<File> filesWithTxt = txtFinder.getFilesWithTxt(filesValidation.findAllValidFilesInDirectoryBreadthSearch(file, extensionOfFile),
                            textForSearch);
                    FileTreeModel fileTreeModel = new FileTreeModel(file.getAbsolutePath(), filesWithTxt);
                    JTree jTree = new JTree(fileTreeModel);
                    jTree.addTreeSelectionListener(new FileTreeSelectionListener(window, extensionOfFile));
                    window.addTree(jTree);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
