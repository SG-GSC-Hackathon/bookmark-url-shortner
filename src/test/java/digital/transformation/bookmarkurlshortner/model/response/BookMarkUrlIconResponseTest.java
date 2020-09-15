package digital.transformation.bookmarkurlshortner.model.response;

import org.junit.Before;

public class BookMarkUrlIconResponseTest {

    private BookMarkUrlIconResponse bookMarkUrlIconResponseUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlIconResponseUnderTest = new BookMarkUrlIconResponse("originalFilename", "contentType", "content".getBytes(), 0, 0);
    }
}
