package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUrl;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlUrlRepository;
import digital.transformation.bookmarkurlshortner.util.BookMarkUrlBaseConversion;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

/**
 * The type Url manager test.
 */
public class BookMarkUrlUrlManagerTest {

    @Mock
    private BookMarkUrlUrlRepository mockUrlRepository;
    @Mock
    private BookMarkUrlBaseConversion mockBaseConversion;

    @InjectMocks
    private BookMarkUrlUrlManager urlManagerUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    /**
     * Test get original url.
     */
    @Test
    public void testGetOriginalUrl() {
        // Setup
        when(mockBaseConversion.decode("input")).thenReturn(0);

        // Configure UrlRepository.findById(...).
        final BookMarkUrlUrl url1 = new BookMarkUrlUrl();
        url1.setId(0);
        url1.setLong_url("long_url");
        url1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        url1.setCard_id(0);
        url1.setShort_url("short_url");
        url1.setExpires_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        final Optional<BookMarkUrlUrl> url = Optional.of(url1);
        when(mockUrlRepository.findById(0)).thenReturn(url);

        // Run the test
        final String result = urlManagerUnderTest.getOriginalUrl("shortUrl");

        // Verify the results
        assertEquals("result", result);
        verify(mockUrlRepository).delete(any(BookMarkUrlUrl.class));
    }
}
