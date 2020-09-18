package digital.transformation.bookmarkurlshortner.repository;


import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCardInGroup;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Group custom repository impl test.
 */
public class BookMarkUrlGroupCustomRepositoryImplTest {

    private BookMarkUrlGroupCustomRepositoryImpl groupCustomRepositoryImplUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        groupCustomRepositoryImplUnderTest = new BookMarkUrlGroupCustomRepositoryImpl();
        groupCustomRepositoryImplUnderTest.entityManager = mock(EntityManager.class);
    }

    /**
     * Test find all active group.
     */
    @Test
    public void testFindAllActiveGroup() {
        // Setup
        when(groupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", BookMarkUrlGroup.class)).thenReturn(null);

        // Run the test
        final List<BookMarkUrlGroup> result = groupCustomRepositoryImplUnderTest.findAllActiveGroup(false);

        // Verify the results
    }

    /**
     * Test remove card from group.
     */
    @Test
    public void testRemoveCardFromGroup() {
        // Setup
        when(groupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", BookMarkUrlCardInGroup.class)).thenReturn(null);

        // Run the test
        groupCustomRepositoryImplUnderTest.removeCardFromGroup(0);

        // Verify the results
    }
}
