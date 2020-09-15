package digital.transformation.bookmarkurlshortner.util;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMarkUrlBaseConversionTest {

    private BookMarkUrlBaseConversion bookMarkUrlBaseConversionUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlBaseConversionUnderTest = new BookMarkUrlBaseConversion();
    }

    @Test
    public void testEncode() {
        // Setup

        // Run the test
        final String result = bookMarkUrlBaseConversionUnderTest.encode(0L);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testDecode() {
        // Setup

        // Run the test
        final long result = bookMarkUrlBaseConversionUnderTest.decode("input");

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }
}
