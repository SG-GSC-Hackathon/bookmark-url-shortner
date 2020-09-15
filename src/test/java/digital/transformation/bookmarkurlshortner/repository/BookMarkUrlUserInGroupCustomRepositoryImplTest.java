package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookMarkUrlUserInGroupCustomRepositoryImplTest {

    private BookMarkUrlUserInGroupCustomRepositoryImpl bookMarkUrlUserInGroupCustomRepositoryImplUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlUserInGroupCustomRepositoryImplUnderTest = new BookMarkUrlUserInGroupCustomRepositoryImpl();
        bookMarkUrlUserInGroupCustomRepositoryImplUnderTest.entityManager = mock(EntityManager.class);
    }

    @Test
    public void testFindAllbyGroup() {
        // Setup
        when(bookMarkUrlUserInGroupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", BookMarkUrlUserInGroup.class)).thenReturn(null);

        // Run the test
        final List<BookMarkUrlUserInGroup> result = bookMarkUrlUserInGroupCustomRepositoryImplUnderTest.findAllbyGroup("groupName");

        // Verify the results
    }
}
