package digital.transformation.bookmarkurlshortner.util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BookMarkUrlDigitalMultipartFileTest {

    private BookMarkUrlDigitalMultipartFile bookMarkUrlDigitalMultipartFileUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlDigitalMultipartFileUnderTest = new BookMarkUrlDigitalMultipartFile("content".getBytes());
    }

    @Test
    public void testGetName() {
        // Setup

        // Run the test
        final String result = bookMarkUrlDigitalMultipartFileUnderTest.getName();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetOriginalFilename() {
        // Setup

        // Run the test
        final String result = bookMarkUrlDigitalMultipartFileUnderTest.getOriginalFilename();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetContentType() {
        // Setup

        // Run the test
        final String result = bookMarkUrlDigitalMultipartFileUnderTest.getContentType();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testIsEmpty() {
        // Setup

        // Run the test
        final boolean result = bookMarkUrlDigitalMultipartFileUnderTest.isEmpty();

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testGetSize() {
        // Setup

        // Run the test
        final long result = bookMarkUrlDigitalMultipartFileUnderTest.getSize();

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void testGetBytes() throws Exception {
        // Setup

        // Run the test
        final byte[] result = bookMarkUrlDigitalMultipartFileUnderTest.getBytes();

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    public void testGetBytes_ThrowsIOException() {
        // Setup

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlDigitalMultipartFileUnderTest.getBytes();
        }).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetInputStream() throws Exception {
        // Setup

        // Run the test
        final InputStream result = bookMarkUrlDigitalMultipartFileUnderTest.getInputStream();

        // Verify the results
    }

    @Test
    public void testGetInputStream_ThrowsIOException() {
        // Setup

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlDigitalMultipartFileUnderTest.getInputStream();
        }).isInstanceOf(IOException.class);
    }

    @Test
    public void testTransferTo() throws Exception {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        bookMarkUrlDigitalMultipartFileUnderTest.transferTo(file);

        // Verify the results
    }

    @Test
    public void testTransferTo_ThrowsIOException() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlDigitalMultipartFileUnderTest.transferTo(file);
        }).isInstanceOf(IOException.class);
    }

    @Test
    public void testTransferTo_ThrowsIllegalStateException() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlDigitalMultipartFileUnderTest.transferTo(file);
        }).isInstanceOf(IllegalStateException.class);
    }
}
