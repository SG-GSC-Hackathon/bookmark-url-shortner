package digital.transformation.bookmarkurlshortner.model.entity;

import org.junit.Before;

public class BookMarkUrlIconTest {

    private BookMarkUrlIcon bookMarkUrlIconUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlIconUnderTest = new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0);
    }
}
