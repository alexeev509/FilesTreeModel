package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.Enumeration;

public class TreeExperiment extends JFrame {

    final   String[]   nodes = new String[]  {"Напитки", "Сладости"};

    final   String[][] leafs = new String[][]{{"Чай", "Кофе", "Коктейль",
            "Сок", "Морс", "Минералка"},
            {"Пирожное", "Мороженое", "Зефир", "Халва"}};

    public TreeExperiment()  {
        super("tree experiment");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String ROOT="Root";
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(ROOT);

        DefaultMutableTreeNode drink = new DefaultMutableTreeNode(nodes[0]);
        DefaultMutableTreeNode sweet = new DefaultMutableTreeNode(nodes[1]);

        root.add(drink);
        root.add(sweet);

        for ( int i = 0; i < leafs[0].length; i++)
            drink.add(new DefaultMutableTreeNode(leafs[0][i], false));


//        drink.add(new DefaultMutableTreeNode(leafs[0][0], false));
        for ( int i = 0; i < leafs[1].length; i++)
            sweet.add(new DefaultMutableTreeNode(leafs[1][i], false));
//        DefaultTreeModel defaultTreeModel=new DefaultTreeModel(root);

        JTree jTree=new JTree(root);

        JPanel contents = new JPanel();
        //add
        contents.add(new JScrollPane(jTree));
        setContentPane(contents);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TreeExperiment();
    }
}
