import gui.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {
    @Test
    void testGetFileExtension() {
        String file = "test.file";

        assertEquals("file", Utils.getFileExtension(file));
    }

}
