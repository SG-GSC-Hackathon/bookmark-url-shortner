package digital.transformation.bookmarkurlshortner.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMarkUrlDigitalUtilTest {

    @Test
    public void testIsValid() {
        assertThat(BookMarkUrlDigitalUtil.isValid("email")).isTrue();
    }

    @Test
    public void testCompressBytes() {
        assertThat(BookMarkUrlDigitalUtil.compressBytes("content".getBytes())).isEqualTo("content".getBytes());
    }

    @Test
    public void testDecompressBytes() {
        assertThat(BookMarkUrlDigitalUtil.decompressBytes("content".getBytes())).isEqualTo("content".getBytes());
    }
}
