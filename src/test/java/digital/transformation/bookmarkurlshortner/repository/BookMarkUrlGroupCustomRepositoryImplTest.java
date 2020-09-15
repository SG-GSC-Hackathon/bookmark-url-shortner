package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookMarkUrlGroupCustomRepositoryImplTest {

    private BookMarkUrlGroupCustomRepositoryImpl bookMarkUrlGroupCustomRepositoryImplUnderTest;

    @Before
    public void setUp() {
        bookMarkUrlGroupCustomRepositoryImplUnderTest = new BookMarkUrlGroupCustomRepositoryImpl();
        bookMarkUrlGroupCustomRepositoryImplUnderTest.entityManager = mock(EntityManager.class);
    }

    @Test
    public void testFindAllActiveGroup() {
        // Setup
        when(bookMarkUrlGroupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", BookMarkUrlGroup.class)).thenReturn(null);

        // Run the test
        final List<BookMarkUrlGroup> result = bookMarkUrlGroupCustomRepositoryImplUnderTest.findAllActiveGroup(false);

        // Verify the results
    }
}
