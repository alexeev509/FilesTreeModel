package com.company.files;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FilesValidation {


    public List<File> findAllValidFilesInDirectoryBreadthSearch(File rootFile,String extension) {
        Queue<File> queue = new LinkedList<>();
        List<File> listOfFiles=new ArrayList<>();

        if(rootFile.isFile()){
            listOfFiles.add(rootFile);
        }
        else {
            queue.add(rootFile);
            while (!queue.isEmpty()){
                File currentFile = queue.poll();
                File[] childrenOfCurrentFile = currentFile.listFiles();
                for (int i = 0; i < childrenOfCurrentFile.length; i++) {
                    if(childrenOfCurrentFile[i].isFile()){
                        if (childrenOfCurrentFile[i].getName().endsWith("." + extension)) {
                            listOfFiles.add(childrenOfCurrentFile[i]);
                        }
                    }else if(childrenOfCurrentFile[i].isDirectory()) {
                        queue.add(childrenOfCurrentFile[i]);
                    }
                }
            }
        }
        return listOfFiles;
    }
}
