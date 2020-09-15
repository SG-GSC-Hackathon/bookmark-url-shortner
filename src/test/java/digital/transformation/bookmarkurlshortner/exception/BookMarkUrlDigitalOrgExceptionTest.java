package digital.transformation.bookmarkurlshortner.exception;

import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class BookMarkUrlDigitalOrgExceptionTest {

    @Mock
    private Throwable mockCause;

    private BookMarkUrlDigitalOrgException bookMarkUrlDigitalOrgExceptionUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        bookMarkUrlDigitalOrgExceptionUnderTest = new BookMarkUrlDigitalOrgException("message", mockCause, false, false);
    }
}
