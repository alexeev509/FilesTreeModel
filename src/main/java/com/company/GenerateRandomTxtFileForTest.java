package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GenerateRandomTxtFileForTest {
    public static void main(String[] args) throws IOException {
//        FileWriter writer = new FileWriter("/Users/apathy/Desktop/ForTask/big.txt");
//        File file = new File("/Users/apathy/Desktop/ForTask/big.txt");
//        while (file.length()<1024*1024*1024) {
//            writer.write("d");
//        }
//        addTxtToEndOfFile();
//        addTxtToFile();
        addTxtToFileFivePages();
    }

    public static void addTxtToEndOfFile() {
        String filePath = "/Users/apathy/Desktop/ForTask/big.txt";
        String text = "Hello world!";

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addTxtToFile() throws IOException {
        String filePath = "/Users/apathy/Desktop/ForTask/first.txt";
        String text = "Hello world!";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

        char[] ch = new char[1024];
        Arrays.fill(ch, 0, ch.length, 'd');
        ch[ch.length - 2] = 'п';
        ch[ch.length - 1] = 'о';
        bufferedWriter.write(ch);
        bufferedWriter.write("ка");
        bufferedWriter.flush();
    }


    public static void addTxtToFileFivePages() throws IOException {
        String filePath = "/Users/apathy/Desktop/ForTask/Second.txt";
        String text = "Hello world!";
        BufferedWriter bufferedWriter = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));

        char[] ch = new char[1024];
        Arrays.fill(ch, 0, ch.length, 'd');
        ch[0] = 'п';
        ch[1] = 'о';
        ch[2] = 'к';
        ch[3] = 'а';
        bufferedWriter.write(ch);
        bufferedWriter.flush();

        ch = new char[1024];
        Arrays.fill(ch, 0, ch.length, 'd');
        ch[0] = 'п';
        ch[1] = 'р';
        ch[2] = 'и';
        ch[3] = 'в';
        ch[4] = 'е';
        ch[5] = 'т';
        bufferedWriter.write(ch);
        bufferedWriter.flush();

        ch = new char[1024];
        Arrays.fill(ch, 0, ch.length, 'd');
        ch[0] = 'с';
        ch[1] = 'е';
        ch[2] = 'р';
        ch[3] = 'в';
        ch[4] = 'е';
        ch[5] = 'р';
        bufferedWriter.write(ch);
        bufferedWriter.flush();

        ch = new char[1024];
        Arrays.fill(ch, 0, ch.length, 'd');
        ch[0] = 'к';
        ch[1] = 'л';
        ch[2] = 'и';
        ch[3] = 'е';
        ch[4] = 'н';
        ch[5] = 'т';
        bufferedWriter.write(ch);
        bufferedWriter.flush();

        ch = new char[1024];
        Arrays.fill(ch, 0, ch.length, 'd');
        ch[0] = 'п';
        ch[1] = 'о';
        ch[2] = 'г';
        ch[3] = 'о';
        ch[4] = 'д';
        ch[5] = 'а';
        bufferedWriter.write(ch);
        bufferedWriter.flush();
    }


}
