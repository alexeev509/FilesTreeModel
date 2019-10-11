package com.company.view;

import com.company.listeners.ButtonOpenDirectoryActionListener;
import com.company.listeners.ScrollAdjustmentListener;
import com.company.txt.classes.TxtReader;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private JFileChooser fileChooser = new JFileChooser();
    private JButton btnOpenDir = new JButton("Выбрать папку для поиска файла");
    private ActionListener btnOpenDirAction;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JTextField textForSearchTextfield = new JTextField("привет", 20);
    private JTextField extensionOfFileTextfield = new JTextField("txt", 20);
    private JLabel textForSearchLabel = new JLabel("Напишите текст для поиска (с учетом регистра):");
    private JLabel extensionOfFileLabel = new JLabel("Напишите расширение для файла (с учетом регистра)");
    private GridBagConstraints c = new GridBagConstraints();
    private JTextArea textArea = new JTextArea();
    private JScrollPane paneOfArea = new JScrollPane(textArea);
    private JScrollPane scrollpaneForTree;
    private boolean textAreaWasAddedOnPanel = false;
    private final static String TITLE = "Files application";
    private final static int WIDTH_OF_THE_FRAME = 700;
    private final static int HEIGHT_OF_THE_FRAME = 700;

    //Customize init block for elements:
    {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        textArea.setLineWrap(true);
        //Set JScrollPane on the top (without this ScrollAdjustmentListener will be scrollBar.getValue() + extent==scrollBar.getMaximum() true
        //because Caret on the top
        textArea.setCaretPosition(0);
        ((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

    }

    public MainWindow() throws HeadlessException {
        super(TITLE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addElementsToWindow();

        setSize(WIDTH_OF_THE_FRAME, HEIGHT_OF_THE_FRAME);
        setVisible(true);
    }

    private void addElementsToWindow() {
        addActionListeners();

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

        setContentPane(panel);
    }

    private void addActionListeners() {
        btnOpenDirAction = new ButtonOpenDirectoryActionListener(this);
        btnOpenDir.addActionListener(btnOpenDirAction);
    }

    private void setXandYForComponents(int x, int y) {
        c.gridx = x;
        c.gridy = y;
    }

    public void addJTextArea(String text, TxtReader txtReader) {

        if (!textAreaWasAddedOnPanel) {
            setXandYForComponents(1, 3);
            c.ipady = 400;
            textArea.setText(text);

            panel.add(paneOfArea, c);
            revalidate();
            paneOfArea.getVerticalScrollBar().addAdjustmentListener(new ScrollAdjustmentListener(txtReader, this));
            textAreaWasAddedOnPanel = true;
        } else {
            textArea.setText(text);
        }
    }

    public void addText(String text, int scrollDirection) {
        StringBuilder textAreaCurrentText=null;

        switch (scrollDirection) {
            case ScrollAdjustmentListener.UP_SCROLL:
                textAreaCurrentText = new StringBuilder(textArea.getText());

                if (textAreaCurrentText.length() > TxtReader.SIZE_OF_BUFFER * 3) {
                    textAreaCurrentText.delete(0, TxtReader.SIZE_OF_BUFFER);
                    textAreaCurrentText.append(text);
                    textArea.setText(textAreaCurrentText.toString());
                } else {
                    textArea.append(text);
                }
//                System.out.println("UP: "+textArea.getText());
                break;
            case ScrollAdjustmentListener.DOWN_SCROLL:
                if (textArea.getText().length() >= TxtReader.SIZE_OF_BUFFER * 3) {
                    textAreaCurrentText = new StringBuilder(textArea.getText().substring(0, TxtReader.SIZE_OF_BUFFER * 3));
                }else {
                    textAreaCurrentText = new StringBuilder(textArea.getText());
                }

                textArea.setText(new StringBuilder(text).append(textAreaCurrentText).toString());
//                System.out.println("DOWN: "+textArea.getText());
                break;
        }
    }


    public void addTree(JTree jTree) {

        if(scrollpaneForTree!=null){
            panel.remove(scrollpaneForTree);
        }
        scrollpaneForTree = new JScrollPane(jTree);
        setXandYForComponents(0, 3);
        c.weighty = 1;
        c.ipady = 400;
        panel.add(scrollpaneForTree, c);
        revalidate();
    }

    public void showMessageBox(String text) {
        JOptionPane.showMessageDialog(null, text);
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
