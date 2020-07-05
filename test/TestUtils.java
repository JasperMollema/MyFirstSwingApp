import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtils {
    @Test
    void testGetFileExtension() {
        String file = "test.file";

        assertEquals("file", Utils.getFileExtension(file));
    }

    @Test
    void testIntegerToBoolean() {
        Integer testNull = null;
        Integer testTrue = 1;
        Integer testFalse = 0;
        Integer testInvalid = -4;
        Integer testInvalid1 = 23;

        assertEquals(null, utils.Utils.integerToBoolean(testNull));
        assertEquals(true, utils.Utils.integerToBoolean(testTrue));
        assertEquals(false, utils.Utils.integerToBoolean(testFalse));
        assertEquals(null, utils.Utils.integerToBoolean(testInvalid));
        assertEquals(null, utils.Utils.integerToBoolean(testInvalid1));
    }

    @Test
    void testIsOtfFont() {
        String ttf = "file.ttf";
        String notOtf = "file.atf";

        assertTrue(Utils.isTrueTypeFont(ttf));
        assertFalse(Utils.isTrueTypeFont(notOtf));
    }
}
