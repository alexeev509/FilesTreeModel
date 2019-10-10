package com.company.TxtFinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//cases:
//1) positionInFileLastPage==positionInFileFirstPage==0==true
//2) 0<positionInFileLastPage<3;  positionInFileFirstPage=0;
//3) positionInFileLastPage=n>=3   positionInFileFirstPage=n-2
//4)
public class TxtReader {
    private long positionInFileLastPage = 0;
    private long positionInFileFirstPage = 0;
    BufferedReader bufferedReader;
    private static final int SIZE_OF_BUFFER = 1024;
    private boolean previousScrollUp = true;
    private String path;

    public void setBufferedReader(String path) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(path));
        positionInFileLastPage = 0;
        positionInFileFirstPage = 0;
        this.path = path;
    }

    public String readNextBytes() throws IOException {
        String text = "";
        char[] chars = new char[SIZE_OF_BUFFER];
//        bufferedReader.skip(positionInFile* SIZE_OF_BUFFER);
        //Если до этого листали вверх и дальше листаем вверх
        if (previousScrollUp) {
            int countOfReadChars = bufferedReader.read(chars);
            if (countOfReadChars != -1) {
                text = new String(chars, 0, countOfReadChars);
                if (positionInFileLastPage < 4) {
                    positionInFileLastPage++;
                } else {
                    positionInFileFirstPage++;
                    positionInFileLastPage++;
                }
            }
        } else {
            //if we down before and up again
            previousScrollUp = true;
            bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.skip(positionInFileLastPage * SIZE_OF_BUFFER);
            positionInFileFirstPage++;//??
            positionInFileLastPage++;//??
            int countOfReadChars = bufferedReader.read(chars);
            text = new String(chars, 0, countOfReadChars);
        }


        return text;
    }

    public String readPreviousBytes() throws IOException {
        String text = "";
        char[] chars = new char[SIZE_OF_BUFFER];
        //Если до этого листали вверх, а теперь начали вниз
//        if (!previousScrollUp) {
//            previousScrollUp = false;
        //Если мы начальна страница не нулевая
        System.out.println("PREV POSITION: " + positionInFileFirstPage);
        if (positionInFileFirstPage - 1 >= 0) {
            bufferedReader = new BufferedReader(new FileReader(path));
            positionInFileFirstPage--;
            positionInFileLastPage--;
            bufferedReader.skip(positionInFileFirstPage * SIZE_OF_BUFFER);
            int countOfReadChars = bufferedReader.read(chars);
            text = new String(chars, 0, countOfReadChars);
            previousScrollUp = false;
        }
//        }


        return text;
    }

    public long getPositionInFileLastPage() {
        return positionInFileLastPage;
    }

    public long getPositionInFileFirstPage() {
        return positionInFileFirstPage;
    }
}
