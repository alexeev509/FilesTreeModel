package com.company.view;

import com.company.listeners.ButtonOpenDirectoryActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private JFileChooser fileChooser=new JFileChooser();
    private JButton btnOpenDir=new JButton("Открыть директорию");
    private ActionListener btnOpenDirAction;
    private JPanel panel=new JPanel(new GridBagLayout());
    private JTextField textForSearchTextfield=new JTextField(20);
    private JTextField extensionOfFileTextfield=new JTextField("txt",20);
    private JLabel textForSearchLabel=new JLabel("Напишите текст для поиска:");
    private JLabel extensionOfFileLabel=new JLabel("Напишите расширение для файла");
    private GridBagConstraints c = new GridBagConstraints();
    private JTextArea textArea = new JTextArea();
    private JTextField ff=new JTextField(20);

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


        c.fill=GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;

        c.weightx=1;
        setXandYForComponents(0,0);
        panel.add(textForSearchLabel,c);
        setXandYForComponents(1,0);
        panel.add(textForSearchTextfield,c);


        setXandYForComponents(0,1);
        panel.add(extensionOfFileLabel,c);
        setXandYForComponents(1,1);
        panel.add(extensionOfFileTextfield,c);


        c.weighty=0.001;
        setXandYForComponents(0,2);
        panel.add(btnOpenDir,c);

//        c.weighty=1;
//        setXandYForComponents(c,0,3);
//        panel.add(new Button("hello"),c);




        setContentPane(panel);
    }
    private void addActionListeners(){
        btnOpenDirAction=new ButtonOpenDirectoryActionListener(this);
        btnOpenDir.addActionListener(btnOpenDirAction);
    }

    public void setXandYForComponents(int x, int y){
        c.gridx = x;
        c.gridy = y;
    }

    public void addJTextArea(String text){
        boolean bb=true;
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if(panel.getComponent(i) instanceof JTextArea){
                bb=false;
            }
        }

        if(bb==true) {
            setXandYForComponents(1, 3);
            c.gridheight=10;
            textArea.setText(text);
            ff.setText(text);
            panel.add(ff, c);
            revalidate();
        }else {
//            textArea.setText(text);
            ff.setText(text);
        }
    }

    public void addTree(JTree jTree){
//        for (int i = 0; i < panel.getComponentCount(); i++) {
//            if(panel.getComponent(i) instanceof JTree){
//                panel.remove(i);
//                System.out.println("component was removed");
//            }
//        }
//        c.fill=GridBagConstraints.HORIZONTAL;
        JScrollPane scrollpane = new JScrollPane(jTree);

        JPanel panel_2 = new JPanel(new BorderLayout());
        panel_2.add(scrollpane,BorderLayout.CENTER);

//        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.);
//        jTree.setSize(new Dimension(40,250));
//        scrollpane.setMinimumSize(new Dimension(this.getWidth()/2,250));
//        scrollpane.setMaximumSize(new Dimension(this.getWidth()/2,250));
        setXandYForComponents(0,3);
        c.weighty=1;
        this.panel.add(panel_2,c);
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
