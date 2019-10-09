package com.company.structures;


import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.*;

public class FileTreeModel implements TreeModel {
    private String root;

    private List<String> listOfNodes = new ArrayList<>();

    public FileTreeModel(String root, List<File> fileCollection) {
        this.root = root;

        for (File entry: fileCollection) {
            String relativePath = entry.toString().replace(root + "/","");

            String nodePath = root;

            for(String nodeName : relativePath.split("/")) {
                nodePath = nodePath + "/" + nodeName;
                if(!listOfNodes.contains(nodePath)) {

                    listOfNodes.add(nodePath);
                }
            }
        }


    }

    public Object getRoot()
    {
        return root;
    }

    public int getChildCount(Object parent) {

        List<String> collectionTmp = getChildCollection(parent.toString());

        return collectionTmp.size();
    }

    public Object getChild(Object parent, int index) {

        List<String> collectionTmp = getChildCollection(parent.toString());

        return collectionTmp.get(index);
    }

    public int getIndexOfChild(Object parent, Object child) {
        List<String> collectionTmp = getChildCollection(parent.toString());

        if(collectionTmp.contains(child.toString())){

            return 1;
        }

        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }

    private List<String>getChildCollection(String parent)
    {
        ArrayList<String> collectionTmp = new  ArrayList<>();

        for (String entry : listOfNodes) {
            if (
                    entry.startsWith(parent)
                            && (entry.split("/").length - parent.split("/").length) == 1
                    ) {
                collectionTmp.add(entry);
            }
        }
        return collectionTmp;
    }

    public boolean isLeaf(Object node) {
        List<String> collectionTmp = getChildCollection(node.toString());

        return collectionTmp.size() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }
}
