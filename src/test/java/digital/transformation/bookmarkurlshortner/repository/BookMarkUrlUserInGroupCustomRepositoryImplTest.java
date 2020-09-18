package digital.transformation.bookmarkurlshortner.repository;


import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type User in group custom repository impl test.
 */
public class BookMarkUrlUserInGroupCustomRepositoryImplTest {

    private BookMarkUrlUserInGroupCustomRepositoryImpl userInGroupCustomRepositoryImplUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        userInGroupCustomRepositoryImplUnderTest = new BookMarkUrlUserInGroupCustomRepositoryImpl();
        userInGroupCustomRepositoryImplUnderTest.entityManager = mock(EntityManager.class);
    }

    /**
     * Test find all user in group by group name.
     */
    @Test
    public void testFindAllUserInGroupByGroupName() {
        // Setup
        when(userInGroupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", BookMarkUrlUserInGroup.class)).thenReturn(null);

        // Run the test
        final List<BookMarkUrlUserInGroup> result = userInGroupCustomRepositoryImplUnderTest.findAllUserInGroupByGroupName("groupName");

        // Verify the results
    }

    /**
     * Test find all user in group by group id.
     */
    @Test
    public void testFindAllUserInGroupByGroupID() {
        // Setup
        when(userInGroupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", BookMarkUrlUserInGroup.class)).thenReturn(null);

        // Run the test
        final List<BookMarkUrlUserInGroup> result = userInGroupCustomRepositoryImplUnderTest.findAllUserInGroupByGroupID(0);

        // Verify the results
    }
}
