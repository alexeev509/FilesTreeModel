import com.company.files.TxtFinder;
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
    private final static String FIRST_TEST_FILE = "textTestFile1.txt";
    private final static String SECOND_TEST_FILE = "textTestFile2.txt";
    private final static String THIRD_TEST_FILE = "textTestFile3.txt";
    private final static String STRING_FOR_FIRST_FILE = "Клиент послал запрос, но сервер не отвечает";
    private final static String STRING_FOR_SECOND_FILE = "сервер не отвечает уже несколько часов";
    private final static String STRING_FOR_THIRD_FILE = "сервер вновь отвечает клиентам";
    private final static String TEXT_FOR_SEARCH_FOR_FIRST_TEST = "сервер не отвечает";
    private final static String STRING_FOR_FIRST_FILE_THIRD_TEST = "серве";
    private final static String STRING_FOR_FIRST_FILE_SECOND_TEST = "сервесервесервесервесервесервесервесервеРсервесервесерве";
    private final static String TEXT_FOR_SEARCH_FOR_SECOND_AND_THIRD_TESTS = "сервеР";
    private final static String TEXT_FOR_SEARCH_FOR_FOURTH_TEST = "привет";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    public List<File> createTxtFilesForFirstTest() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        File file2;
        File file3;
        try {
            file1 = temporaryFolder.newFile(FIRST_TEST_FILE);
            file2 = temporaryFolder.newFile(SECOND_TEST_FILE);
            file3 = temporaryFolder.newFile(THIRD_TEST_FILE);

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            bufferedWriter.write(STRING_FOR_FIRST_FILE);
            bufferedWriter.flush();

            bufferedWriter = new BufferedWriter(new FileWriter(file2));
            bufferedWriter.write(STRING_FOR_SECOND_FILE);
            bufferedWriter.flush();

            bufferedWriter = new BufferedWriter(new FileWriter(file3));
            bufferedWriter.write(STRING_FOR_THIRD_FILE);
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
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, TEXT_FOR_SEARCH_FOR_FIRST_TEST);

        List<File> expected = new ArrayList<>(Arrays.asList(filesForTest.get(0), filesForTest.get(1)));

        Assert.assertEquals(expected, actual);
    }


    public List<File> createTxtFilesForSecondTest() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        try {
            file1 = temporaryFolder.newFile(FIRST_TEST_FILE);

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            bufferedWriter.write(STRING_FOR_FIRST_FILE_SECOND_TEST);
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
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, TEXT_FOR_SEARCH_FOR_SECOND_AND_THIRD_TESTS);

        List<File> expected = new ArrayList<>(Arrays.asList(filesForTest.get(0)));

        Assert.assertEquals(expected, actual);
    }

    public List<File> createTxtFilesForThirdTest_BigFile() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        try {
            file1 = temporaryFolder.newFile(FIRST_TEST_FILE);

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            textGeneratorBigFile(bufferedWriter, file1);
            bufferedWriter.flush();
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        return Arrays.asList(file1);

    }

    public void textGeneratorBigFile(BufferedWriter bufferedWriter, File file) throws IOException {
//        int fileSize=1024*1024*1024;
        int fileSize = 1024 * 1024;
        while (file.length() < fileSize) {
            bufferedWriter.write(STRING_FOR_FIRST_FILE_THIRD_TEST);
        }
        bufferedWriter.write(TEXT_FOR_SEARCH_FOR_SECOND_AND_THIRD_TESTS);
    }

    @Test
    public void testGetFilesWithTxtMethod_Third_BigFile() throws Exception {
        TxtFinder txtFinder = new TxtFinder();
        List<File> filesForTest = createTxtFilesForThirdTest_BigFile();
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, TEXT_FOR_SEARCH_FOR_SECOND_AND_THIRD_TESTS);

        List<File> expected = new ArrayList<>(filesForTest);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetFilesWithTxtMethod_Fourth_OneSearchWordOnTwoPages() throws Exception {
        TxtFinder txtFinder = new TxtFinder();
        List<File> filesForTest = createTxtFilesForFourthTest_OneSearchWordOnTwoPages();
        List<File> actual = txtFinder.getFilesWithTxt(filesForTest, TEXT_FOR_SEARCH_FOR_FOURTH_TEST);

        List<File> expected = new ArrayList<>(filesForTest);

        Assert.assertEquals(expected, actual);
    }


    public List<File> createTxtFilesForFourthTest_OneSearchWordOnTwoPages() throws IOException {
        BufferedWriter bufferedWriter = null;
        File file1;
        try {
            file1 = temporaryFolder.newFile(FIRST_TEST_FILE);

            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            textGeneratorWhereSearchWordOnTwoPages(bufferedWriter, file1);
            bufferedWriter.flush();
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        return Arrays.asList(file1);

    }

    public void textGeneratorWhereSearchWordOnTwoPages(BufferedWriter bufferedWriter, File file) throws IOException {
        char[] ch = new char[1024];
        Arrays.fill(ch, 0, ch.length, 'd');
        ch[ch.length - 2] = 'п';
        ch[ch.length - 1] = 'р';
        bufferedWriter.write(ch);
        bufferedWriter.write("ивет");
    }
}
