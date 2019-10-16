package com.company.txt.classes;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFinder {


    /*  ( We can't use pattern match, because first part of search word can be on first block
    * second part of the word on second !)
    * Algorithm:
    *  txtForSearch = "server"
    *  text of file: "absserver"
    *  massForSearchWord=[0 0 0 0 0 0]
    *  counter = 0;
    *  s == a - no
    *  s == b - no
    *  s == s - yes
    *  massForSearchWord=[1 0 0 0 0 0]
    *  counter = 1;
    *  e == s - no
    *  s == s - yes
    *  massForSearchWord=[1 0 0 0 0 0]
    *  counter = 1;
    *  e == e - yes
    *  massForSearchWord=[1 1 0 0 0 0]
    *  counter = 2;
    *  etc
    *  This algorithm can be slow for case: "serveserveserve...server"
    * */
    public List<File> getFilesWithTxt(List<File> files, String txtForSearch) throws IOException {
        List<File> filesWithSearchTxt = new ArrayList<>();

        for (int j = 0; j < files.size(); j++) {
            File currentFile = files.get(j);
            int[] massForSearchWord = new int[txtForSearch.length()];
            BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFile.getAbsolutePath()));
            char[] buffer;
            int inputLength = 0;
            int counter = 0;
            boolean txtHasBeenFound = false;

            while (!txtHasBeenFound) {
                buffer = new char[1024];
                inputLength = bufferedReader.read(buffer);
                if (inputLength == -1) {
                    break;
                }
                String str = new String(buffer, 0, inputLength);
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == txtForSearch.charAt(counter)) {
                        massForSearchWord[counter++] = 1;
                        if (counter == massForSearchWord.length - 1) {
//                            System.out.println("we have find word in file " + currentFile.getName());
                            filesWithSearchTxt.add(currentFile);
                            txtHasBeenFound = true;
                            break;
                        }
                    } else if (str.charAt(i) == txtForSearch.charAt(0)) {
                        massForSearchWord = new int[txtForSearch.length()];
                        counter = 1;
                    } else {
                        massForSearchWord = new int[txtForSearch.length()];
                        counter = 0;
                    }
                }
            }
            bufferedReader.close();
        }
        return filesWithSearchTxt;
    }

}
