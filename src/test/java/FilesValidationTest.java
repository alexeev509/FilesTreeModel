import com.company.TxtFinder.FilesValidation;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class FilesValidationTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void testFindAllValidFilesInDirectoryBreadthSearchMethod() throws Exception {
        FilesValidation filesValidation = new FilesValidation();
        File test1 = temporaryFolder.newFile("test1.txt");
        File test2 = temporaryFolder.newFile("test2.log");
        File test3 = temporaryFolder.newFolder("TestFolder");
        File test4 = temporaryFolder.newFile("test4.txt");

        File mockedFile = Mockito.mock(File.class);
        File mockedFileInside = Mockito.mock(File.class);
        when(mockedFile.getPath()).thenReturn("/anyFolder/");
        when(mockedFile.isFile()).thenReturn(false);
        when(mockedFile.listFiles()).thenReturn(
                new File[]{test1,
                        test2,
                        test3,
                        mockedFileInside});
        when(mockedFileInside.getPath()).thenReturn("innerFolder/");
        when(mockedFileInside.isFile()).thenReturn(false);
        when(mockedFileInside.isDirectory()).thenReturn(true);
        when(mockedFileInside.listFiles()).thenReturn( new File[]{test4});


        List<File> actual = filesValidation.findAllValidFilesInDirectoryBreadthSearch(mockedFile, "txt");
        List<File> expected= Arrays.asList(new File[]{test1,test4});
        Assert.assertEquals(expected,actual);
    }
}
