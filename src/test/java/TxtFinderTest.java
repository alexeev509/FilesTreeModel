import com.company.TxtFinder.TxtFinder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtFinderTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    public List<File> createTxtFilesForFirstTest() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        File file2;
        File file3;
        try {
            file1 = temporaryFolder.newFile("textTestFile1.txt");
            file2 = temporaryFolder.newFile("textTestFile2.txt");
            file3 = temporaryFolder.newFile("textTestFile3.txt");

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            bufferedWriter.write("Клиент послал запрос, но сервер не отвечает");
            bufferedWriter.flush();

            bufferedWriter = new BufferedWriter(new FileWriter(file2));
            bufferedWriter.write("сервер не отвечает уже несколько часов");
            bufferedWriter.flush();

            bufferedWriter = new BufferedWriter(new FileWriter(file3));
            bufferedWriter.write("сервер вновь отвечает клиентам");
            bufferedWriter.flush();
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        return Arrays.asList(file1, file2, file3);

    }

    @Test
    public void testGetFilesWithTxtMethod_First() throws Exception {
        TxtFinder txtFinder = new TxtFinder();
        List<File> filesForTest = createTxtFilesForFirstTest();
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, "сервер не отвечает");

        List<File> expected = new ArrayList<>(Arrays.asList(filesForTest.get(0), filesForTest.get(1)));

        Assert.assertEquals(expected, actual);
    }


    public List<File> createTxtFilesForSecondTest() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        try {
            file1 = temporaryFolder.newFile("textTestFile1.txt");

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            bufferedWriter.write("сервесервесервесервесервесервесервесервеРсервесервесерве");
            bufferedWriter.flush();
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        return Arrays.asList(file1);

    }

    @Test
    public void testGetFilesWithTxtMethod_Second() throws Exception {
        TxtFinder txtFinder = new TxtFinder();
        List<File> filesForTest = createTxtFilesForSecondTest();
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, "сервеР");

        List<File> expected = new ArrayList<>(Arrays.asList(filesForTest.get(0)));

        Assert.assertEquals(expected, actual);
    }

    public List<File> createTxtFilesForThirdTest() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        try {
            file1 = temporaryFolder.newFile("textTestFile1.txt");

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            textGeneratorBigFile(bufferedWriter,file1);
            bufferedWriter.flush();
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        return Arrays.asList(file1);

    }

    public void textGeneratorBigFile(BufferedWriter bufferedWriter,File file) throws IOException {
        String txt="серве";
//        int fileSize=1024*1024*100;
        int fileSize=1024*1024;
        while (file.length()<fileSize) {
            bufferedWriter.write(txt);
        }
        System.out.println(file.length());
        bufferedWriter.write("сервеР");
    }

    @Test
    public void testGetFilesWithTxtMethod_Third() throws Exception {
        TxtFinder txtFinder = new TxtFinder();
        List<File> filesForTest = createTxtFilesForThirdTest();
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, "сервеР");

        List<File> expected = new ArrayList<>(filesForTest);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetFilesWithTxtMethod_Fourth() throws Exception {
        TxtFinder txtFinder = new TxtFinder();
        List<File> filesForTest = createTxtFilesForFourthTest();
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, "привет");

        List<File> expected = new ArrayList<>(filesForTest);

        Assert.assertEquals(expected, actual);
    }


    public List<File> createTxtFilesForFourthTest() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        try {
            file1 = temporaryFolder.newFile("textTestFile1.txt");

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            textGeneratorWhereSearchWordOnTwoPages(bufferedWriter,file1);
            bufferedWriter.flush();
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        return Arrays.asList(file1);

    }

    public void textGeneratorWhereSearchWordOnTwoPages(BufferedWriter bufferedWriter,File file) throws IOException {
        char[] ch=new char[1024];
        Arrays.fill(ch,0,ch.length, 'd');
        ch[ch.length-2]='п';
        ch[ch.length-1]='р';
        bufferedWriter.write(ch);
        bufferedWriter.write("ивет");
    }
}
