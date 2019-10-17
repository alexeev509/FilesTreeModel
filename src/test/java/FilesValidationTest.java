import com.company.files.FilesValidation;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class FilesValidationTest {
    private final static String NAME_OF_THE_FIRST_FILE = "test1.txt";
    private final static String NAME_OF_THE_SECOND_FILE = "test2.log";
    private final static String NAME_OF_THE_Folder = "TestFolder";
    private final static String NAME_OF_THE_THIRD_FILE = "test4.txt";
    private final static String MOCKED_RETURNING_PATH = "/anyFolder/";
    private final static String MOCKED_RETURNING_PATH_FOR_INSIDE_FILE = "innerFolder/";
    private final static String VALID_EXTENSION = "txt";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void testFindAllValidFilesInDirectoryBreadthSearchMethod() throws Exception {
        FilesValidation filesValidation = new FilesValidation();
        File test1 = temporaryFolder.newFile(NAME_OF_THE_FIRST_FILE);
        File test2 = temporaryFolder.newFile(NAME_OF_THE_SECOND_FILE);
        File test3 = temporaryFolder.newFolder(NAME_OF_THE_Folder);
        File test4 = temporaryFolder.newFile(NAME_OF_THE_THIRD_FILE);

        File mockedFile = Mockito.mock(File.class);
        File mockedFileInside = Mockito.mock(File.class);
        when(mockedFile.getPath()).thenReturn(MOCKED_RETURNING_PATH);
        when(mockedFile.isFile()).thenReturn(false);
        when(mockedFile.listFiles()).thenReturn(
                new File[]{test1,
                        test2,
                        test3,
                        mockedFileInside});
        when(mockedFileInside.getPath()).thenReturn(MOCKED_RETURNING_PATH_FOR_INSIDE_FILE);
        when(mockedFileInside.isFile()).thenReturn(false);
        when(mockedFileInside.isDirectory()).thenReturn(true);
        when(mockedFileInside.listFiles()).thenReturn( new File[]{test4});


        List<File> actual = filesValidation.findAllValidFilesInDirectoryBreadthSearch(mockedFile, VALID_EXTENSION);
        List<File> expected= Arrays.asList(new File[]{test1,test4});
        Assert.assertEquals(expected,actual);
    }
}
