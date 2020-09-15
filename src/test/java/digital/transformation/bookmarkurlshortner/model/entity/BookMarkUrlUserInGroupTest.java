package digital.transformation.bookmarkurlshortner.model.entity;

import org.junit.Before;

public class BookMarkUrlUserInGroupTest {

    private BookMarkUrlUserInGroup bookMarkUrlUserInGroupUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlUserInGroupUnderTest = new BookMarkUrlUserInGroup("user", "groupName", "admin");
    }
}
