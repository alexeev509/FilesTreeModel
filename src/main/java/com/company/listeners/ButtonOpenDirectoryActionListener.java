package com.company.listeners;

import com.company.TxtFinder.FilesValidation;
import com.company.TxtFinder.TxtFinder;
import com.company.structures.FileTreeModel;
import com.company.view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ButtonOpenDirectoryActionListener implements ActionListener {

    private FilesValidation filesValidation=new FilesValidation();
    private TxtFinder txtFinder=new TxtFinder();
    private MainWindow window;

    public ButtonOpenDirectoryActionListener(MainWindow window) {
        this.window=window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = window.getFileChooser();
        int ret = fileChooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getCurrentDirectory();

            try {
                List<File> filesWithTxt = txtFinder.getFilesWithTxt(filesValidation.findAllValidFilesInDirectoryBreadthSearch(file, window.getExtensionOfFileTextfield().getText()),
                        window.getTextForSearchTextfield().getText());
                FileTreeModel fileTreeModel=new FileTreeModel(file.getAbsolutePath(),filesWithTxt);
                final JTree jTree = new JTree(fileTreeModel);
                jTree.addTreeSelectionListener(new FileTreeSelectionListener(window));
                window.addTree(jTree);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
