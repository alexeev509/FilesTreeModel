package com.company.TxtFinder;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFinder {


//    public static void main(String[] args) throws IOException {
//        String txtToFind = "Hello";
//        int[] mass=new int[txtToFind.length()];
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/apathy/Desktop/ForTask/big.txt"));
//        char[] buffer = new char[100];
//        int inputLenght = 0;
//        int counter=0;
//        while (inputLenght != -1) {
//            buffer=new char[1024];
//            inputLenght = bufferedReader.read(buffer);
//            String str = new String(buffer);
//            System.out.println(str);
//            for(int i=0;i<str.length();i++){
//                if(str.charAt(i)==txtToFind.charAt(counter)){
//                    mass[counter++]=1;
//                    if(counter==mass.length-1){
//                        System.out.println("we have find!");
//                        break;
//                    }
//                }else if(str.charAt(i)==txtToFind.charAt(0)) {
//                    mass=new int[txtToFind.length()];
//                    counter=1;
//                }else {
//                    mass=new int[txtToFind.length()];
//                    counter=0;
//                }
//            }
//        }
//    }
    //нельзя использовать pattern match из-за возможного стыка блоков при чтении

    public List<File> getFilesWithTxt(List<File> files, String txtForSearch) throws IOException {
        List<File> filesWithSearchTxt = new ArrayList<>();

        for (int j = 0; j < files.size(); j++) {
            File currentFile = files.get(j);
            int[] mass = new int[txtForSearch.length()];
            BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFile.getAbsolutePath()));
            char[] buffer;
            int inputLenght = 0;
            int counter = 0;
            boolean txtHasBeenFound = false;

            while (!txtHasBeenFound) {
                buffer = new char[1024];
                inputLenght = bufferedReader.read(buffer);
                if (inputLenght == -1) {
                    break;
                }
                String str = new String(buffer, 0, inputLenght);
                System.out.println(str);
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == txtForSearch.charAt(counter)) {
                        mass[counter++] = 1;
                        if (counter == mass.length - 1) {
                            System.out.println("we have find word in file " + currentFile.getName());
                            filesWithSearchTxt.add(currentFile);
                            txtHasBeenFound = true;
                            break;
                        }
                    } else if (str.charAt(i) == txtForSearch.charAt(0)) {
                        mass = new int[txtForSearch.length()];
                        counter = 1;
                    } else {
                        mass = new int[txtForSearch.length()];
                        counter = 0;
                    }
                }
            }
        }
        return filesWithSearchTxt;
    }

}
