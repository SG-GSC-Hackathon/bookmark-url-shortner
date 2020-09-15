package digital.transformation.bookmarkurlshortner.model.mapper;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMarkUrlStringListConverterTest {

    private BookMarkUrlStringListConverter bookMarkUrlStringListConverterUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlStringListConverterUnderTest = new BookMarkUrlStringListConverter();
    }

    @Test
    public void testConvertToDatabaseColumn() {
        // Setup
        final List<String> stringList = Arrays.asList("value");

        // Run the test
        final String result = bookMarkUrlStringListConverterUnderTest.convertToDatabaseColumn(stringList);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testConvertToEntityAttribute() {
        // Setup
        final List<String> expectedResult = Arrays.asList("value");

        // Run the test
        final List<String> result = bookMarkUrlStringListConverterUnderTest.convertToEntityAttribute("string");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
