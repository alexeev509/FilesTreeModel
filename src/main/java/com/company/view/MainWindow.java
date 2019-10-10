package com.company.view;

import com.company.TxtFinder.TxtReader;
import com.company.listeners.ButtonOpenDirectoryActionListener;
import com.company.listeners.ScrollAdjustmentListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class MainWindow extends JFrame {

    private JFileChooser fileChooser = new JFileChooser();
    private JButton btnOpenDir = new JButton("Открыть директорию");
    private ActionListener btnOpenDirAction;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JTextField textForSearchTextfield = new JTextField("привет",20);
    private JTextField extensionOfFileTextfield = new JTextField("txt", 20);
    private JLabel textForSearchLabel = new JLabel("Напишите текст для поиска:");
    private JLabel extensionOfFileLabel = new JLabel("Напишите расширение для файла");
    private GridBagConstraints c = new GridBagConstraints();
    private JTextArea textArea = new JTextArea();

    {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public MainWindow() throws HeadlessException {
        super("Files application");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addElementsToWindow();

        setSize(700, 700);
        setVisible(true);
    }

    private void addElementsToWindow() {
        addActionListeners();

//        panel.setLayout();


        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;

        c.weightx = 1;
        setXandYForComponents(0, 0);
        panel.add(textForSearchLabel, c);
        setXandYForComponents(1, 0);
        panel.add(textForSearchTextfield, c);


        setXandYForComponents(0, 1);
        panel.add(extensionOfFileLabel, c);
        setXandYForComponents(1, 1);
        panel.add(extensionOfFileTextfield, c);


        c.weighty = 0.001;
        setXandYForComponents(0, 2);
        panel.add(btnOpenDir, c);

//        c.weighty=1;
//        setXandYForComponents(c,0,3);
//        panel.add(new Button("hello"),c);


        setContentPane(panel);
    }

    private void addActionListeners() {
        btnOpenDirAction = new ButtonOpenDirectoryActionListener(this);
        btnOpenDir.addActionListener(btnOpenDirAction);
    }

    public void setXandYForComponents(int x, int y) {
        c.gridx = x;
        c.gridy = y;
    }

    public void addJTextArea(String text, TxtReader txtReader) {
        boolean bb = true;
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JTextArea) {
                bb = false;
            }
        }

        if (bb == true) {
            setXandYForComponents(1, 3);
            c.ipady = 400;
            textArea.setLineWrap(true);
            textArea.setText(text);
            final JScrollPane paneOfArea = new JScrollPane(textArea);
            //Set JScrollPane on the top (without this ScrollAdjustmentListener will be scrollBar.getValue() + extent==scrollBar.getMaximum() true
            //because Caret on the top
            textArea.setCaretPosition(0);
            ((DefaultCaret)textArea.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
            panel.add(paneOfArea, c);
            revalidate();
            paneOfArea.getVerticalScrollBar().addAdjustmentListener(new ScrollAdjustmentListener(txtReader, this));

        } else {
            textArea.setText(text);
        }
    }

    public void addText(String text, int scrollDirection) {
        StringBuilder textAreaCurrentText;
        switch (scrollDirection) {
            case ScrollAdjustmentListener.UP_SCROLL:
                textAreaCurrentText = new StringBuilder(textArea.getText());

                if (textAreaCurrentText.length() > 1024 * 3) {
                    textAreaCurrentText.delete(0, 1024);
                    textAreaCurrentText.append(text);
                    textArea.setText(textAreaCurrentText.toString());
                } else {
                    System.out.println("Len " + textAreaCurrentText.length());
                    textArea.append(text);
                }
                System.out.println("UP: "+textArea.getText());
                break;
            case ScrollAdjustmentListener.DOWN_SCROLL:
                textAreaCurrentText = new StringBuilder(textArea.getText().substring(0,1024*3));
                System.out.println(textArea.getText().substring(0,1024*3)+" Las symbol");
//                textAreaCurrentText.delete(1024 * 2, 1024 * 3 + 1);
//                textAreaCurrentText.delete(0, 1024);
                textArea.setText(new StringBuilder(text).append(textAreaCurrentText).toString());
                System.out.println("DOWN: "+textArea.getText());
                break;
        }
    }

    int height;

    public void addTree(JTree jTree) {

        JScrollPane scrollpane = new JScrollPane(jTree);

        height = scrollpane.getHeight();
        setXandYForComponents(0, 3);
        c.weighty = 1;
        c.ipady = 400;
        panel.add(scrollpane, c);
        revalidate();
    }

    public JTextField getTextForSearchTextfield() {
        return textForSearchTextfield;
    }

    public JTextField getExtensionOfFileTextfield() {
        return extensionOfFileTextfield;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
