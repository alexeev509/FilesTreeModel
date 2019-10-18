package com.company.listeners;

import com.company.files.TxtReader;
import com.company.view.MainWindow;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileTreeSelectionListener implements TreeSelectionListener {
    private TxtReader txtReader=new TxtReader();
    private String extensionOfFile;
    MainWindow window;

    public FileTreeSelectionListener(MainWindow window, String extensionOfFile) throws FileNotFoundException {
        this.window=window;
        this.extensionOfFile = extensionOfFile;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        String node = (String) e.getPath().getLastPathComponent();
        if (System.getProperty("os.name").startsWith("Windows")) {
            String[] split = node.split("/");
            node = split[split.length - 1];
        }

        if (node.endsWith("." + extensionOfFile)) {
            try {
                txtReader.setBufferedReader(node);
                String text = txtReader.readNextBytes();
                window.addJTextArea(text,txtReader);
            }  catch (IOException e1) {

            }
        }
    }
}
