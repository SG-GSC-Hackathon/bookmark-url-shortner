package digital.transformation.bookmarkurlshortner.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * The type Digital util test.
 */
public class BookMarkUrlDigitalUtilTest {

    /**
     * Test is email valid.
     */
    @Test
    public void testIsEmailValid() {
        assertTrue(BookMarkUrlDigitalUtil.isEmailValid("email"));
    }

    /**
     * Test is url valid.
     */
    @Test
    public void testIsUrlValid() {
        assertTrue(BookMarkUrlDigitalUtil.isUrlValid("url"));
    }

    /**
     * Test compress bytes.
     */
    @Test
    public void testCompressBytes() {
        assertEquals("content".getBytes(), BookMarkUrlDigitalUtil.compressBytes("content".getBytes()));
    }

    /**
     * Test decompress bytes.
     */
    @Test
    public void testDecompressBytes() {
        assertEquals("content".getBytes(), BookMarkUrlDigitalUtil.decompressBytes("content".getBytes()));
    }
}
