package com.company.TxtFinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TxtReader {
    private long positionInFile=0;
    BufferedReader bufferedReader;
    private static final int SIZE_OF_BUFFER =1024;

    public void setBufferedReader(String path) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(path));
        positionInFile=0;
    }

    public String readNextBytes() throws IOException {
        bufferedReader.skip(positionInFile* SIZE_OF_BUFFER);
        char[] chars = new char[SIZE_OF_BUFFER];
        bufferedReader.read(chars);//-1??
        String text = new String(chars);
        positionInFile++;
        return text;
    }

    public String readPreviousBytes() throws IOException {
        positionInFile-=2;
        bufferedReader.skip(positionInFile* SIZE_OF_BUFFER);
        char[] chars = new char[SIZE_OF_BUFFER];
        bufferedReader.read(chars);//-1??
        String text = new String(chars);
        return text;
    }
}
