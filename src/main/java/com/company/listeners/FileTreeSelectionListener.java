package com.company.listeners;

import com.company.TxtFinder.TxtReader;
import com.company.view.MainWindow;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileTreeSelectionListener implements TreeSelectionListener {
    private TxtReader txtReader=new TxtReader();
    MainWindow window;

    public FileTreeSelectionListener(MainWindow window) throws FileNotFoundException {
        this.window=window;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        String node = (String) e.getPath().getLastPathComponent();

        if (node.endsWith(".txt")) {
            try {
                txtReader.setBufferedReader(node);
                String text = txtReader.readNextBytes();
                window.addJTextArea(text);
            }  catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
