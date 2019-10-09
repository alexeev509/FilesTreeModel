package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateRandomTxtFileForTest {
    public static void main(String[] args) throws IOException {
//        FileWriter writer = new FileWriter("/Users/apathy/Desktop/ForTask/big.txt");
//        File file = new File("/Users/apathy/Desktop/ForTask/big.txt");
//        while (file.length()<1024*1024*1024) {
//            writer.write("d");
//        }
        addTxtToEndOfFile();

    }
    public static void addTxtToEndOfFile(){
        String filePath = "/Users/apathy/Desktop/ForTask/big.txt";
        String text = "Hello world!";

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
