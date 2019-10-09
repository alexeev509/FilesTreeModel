import org.junit.Test;

import java.io.File;

public class MainTest {
    @Test
    public void name() throws Exception {
        File file = new File("/Users/apathy/Desktop/ForTask");
        for (File s : file.listFiles()) {
            System.out.println(s);
        }
    }
}
