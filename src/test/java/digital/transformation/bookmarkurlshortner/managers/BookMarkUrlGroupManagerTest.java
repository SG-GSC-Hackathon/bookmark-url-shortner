package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlGroupResponse;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlGroupRepository;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlUserInGroupRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookMarkUrlGroupManagerTest {

    @Mock
    private BookMarkUrlGroupRepository mockGroupRepository;
    @Mock
    private BookMarkUrlUserInGroupRepository mockUserInGroupRepository;

    @InjectMocks
    private BookMarkUrlGroupManager bookMarkUrlGroupManagerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetGroup() {
        // Setup

        // Configure BookMarkUrlGroupRepository.findById(...).
        final BookMarkUrlGroup bookMarkUrlGroup1 = new BookMarkUrlGroup();
        bookMarkUrlGroup1.setId(0);
        bookMarkUrlGroup1.setName("name");
        bookMarkUrlGroup1.setDescription("description");
        bookMarkUrlGroup1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup1.setCreated_by("created_by");
        bookMarkUrlGroup1.setTribe("tribe");
        bookMarkUrlGroup1.setTeam("team");
        bookMarkUrlGroup1.setComponent("component");
        bookMarkUrlGroup1.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup1.setUpdated_by("updated_by");
        final Optional<BookMarkUrlGroup> bookMarkUrlGroup = Optional.of(bookMarkUrlGroup1);
        when(mockGroupRepository.findById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        final BookMarkUrlGroup result = bookMarkUrlGroupManagerUnderTest.getGroup(0);

        // Verify the results
    }

    @Test
    public void testFindAllActiveGroup() {
        // Setup

        // Configure BookMarkUrlGroupRepository.findAllActiveGroup(...).
        final BookMarkUrlGroup bookMarkUrlGroup = new BookMarkUrlGroup();
        bookMarkUrlGroup.setId(0);
        bookMarkUrlGroup.setName("name");
        bookMarkUrlGroup.setDescription("description");
        bookMarkUrlGroup.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup.setCreated_by("created_by");
        bookMarkUrlGroup.setTribe("tribe");
        bookMarkUrlGroup.setTeam("team");
        bookMarkUrlGroup.setComponent("component");
        bookMarkUrlGroup.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup.setUpdated_by("updated_by");
        final List<BookMarkUrlGroup> bookMarkUrlGroups = Arrays.asList(bookMarkUrlGroup);
        when(mockGroupRepository.findAllActiveGroup(false)).thenReturn(bookMarkUrlGroups);

        // Run the test
        final List<BookMarkUrlGroup> result = bookMarkUrlGroupManagerUnderTest.findAllActiveGroup();

        // Verify the results
    }

    @Test
    public void testCreateGroup() {
        // Setup
        final BookMarkUrlGroupRequest bookMarkUrlGroupRequest = new BookMarkUrlGroupRequest();
        bookMarkUrlGroupRequest.setName("name");
        bookMarkUrlGroupRequest.setDescription("description");
        bookMarkUrlGroupRequest.setCreated_by("created_by");
        bookMarkUrlGroupRequest.setTribe("tribe");
        bookMarkUrlGroupRequest.setTeam("team");
        bookMarkUrlGroupRequest.setComponent("component");
        bookMarkUrlGroupRequest.setUpdated_by("updated_by");
        bookMarkUrlGroupRequest.setAdmin(Arrays.asList("value"));

        // Configure BookMarkUrlGroupRepository.save(...).
        final BookMarkUrlGroup bookMarkUrlGroup = new BookMarkUrlGroup();
        bookMarkUrlGroup.setId(0);
        bookMarkUrlGroup.setName("name");
        bookMarkUrlGroup.setDescription("description");
        bookMarkUrlGroup.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup.setCreated_by("created_by");
        bookMarkUrlGroup.setTribe("tribe");
        bookMarkUrlGroup.setTeam("team");
        bookMarkUrlGroup.setComponent("component");
        bookMarkUrlGroup.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup.setUpdated_by("updated_by");
        when(mockGroupRepository.save(any(BookMarkUrlGroup.class))).thenReturn(bookMarkUrlGroup);

        // Run the test
        final BookMarkUrlGroupResponse result = bookMarkUrlGroupManagerUnderTest.createGroup(bookMarkUrlGroupRequest);

        // Verify the results
    }

    @Test
    public void testGetGroupById() {
        // Setup

        // Configure BookMarkUrlGroupRepository.findById(...).
        final BookMarkUrlGroup bookMarkUrlGroup1 = new BookMarkUrlGroup();
        bookMarkUrlGroup1.setId(0);
        bookMarkUrlGroup1.setName("name");
        bookMarkUrlGroup1.setDescription("description");
        bookMarkUrlGroup1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup1.setCreated_by("created_by");
        bookMarkUrlGroup1.setTribe("tribe");
        bookMarkUrlGroup1.setTeam("team");
        bookMarkUrlGroup1.setComponent("component");
        bookMarkUrlGroup1.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup1.setUpdated_by("updated_by");
        final Optional<BookMarkUrlGroup> bookMarkUrlGroup = Optional.of(bookMarkUrlGroup1);
        when(mockGroupRepository.findById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        final BookMarkUrlGroup result = bookMarkUrlGroupManagerUnderTest.getGroupById(0);

        // Verify the results
    }

    @Test
    public void testDeleteGroup() {
        // Setup

        // Run the test
        bookMarkUrlGroupManagerUnderTest.deleteGroup(0);

        // Verify the results
        verify(mockGroupRepository).deleteById(0);
    }

    @Test
    public void testAddUserToGroup() {
        // Setup

        // Configure BookMarkUrlUserInGroupRepository.save(...).
        final BookMarkUrlUserInGroup bookMarkUrlUserInGroup = new BookMarkUrlUserInGroup("user", "groupName", "admin");
        when(mockUserInGroupRepository.save(any(BookMarkUrlUserInGroup.class))).thenReturn(bookMarkUrlUserInGroup);

        // Run the test
        bookMarkUrlGroupManagerUnderTest.addUserToGroup("user", "groupName", "admin");

        // Verify the results
    }

    @Test
    public void testRemoveUserToGroup() {
        // Setup

        // Configure BookMarkUrlUserInGroupRepository.findAllbyGroup(...).
        final List<BookMarkUrlUserInGroup> bookMarkUrlUserInGroups = Arrays.asList(new BookMarkUrlUserInGroup("user", "groupName", "admin"));
        when(mockUserInGroupRepository.findAllbyGroup("groupName")).thenReturn(bookMarkUrlUserInGroups);

        // Run the test
        bookMarkUrlGroupManagerUnderTest.removeUserToGroup("user", "groupName", "admin");

        // Verify the results
        verify(mockUserInGroupRepository).deleteById(0);
    }

    @Test
    public void testGetAllGroupManager() {
        // Setup

        // Configure BookMarkUrlGroupRepository.findAll(...).
        final BookMarkUrlGroup bookMarkUrlGroup = new BookMarkUrlGroup();
        bookMarkUrlGroup.setId(0);
        bookMarkUrlGroup.setName("name");
        bookMarkUrlGroup.setDescription("description");
        bookMarkUrlGroup.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup.setCreated_by("created_by");
        bookMarkUrlGroup.setTribe("tribe");
        bookMarkUrlGroup.setTeam("team");
        bookMarkUrlGroup.setComponent("component");
        bookMarkUrlGroup.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroup.setUpdated_by("updated_by");
        final List<BookMarkUrlGroup> bookMarkUrlGroups = Arrays.asList(bookMarkUrlGroup);
        when(mockGroupRepository.findAll()).thenReturn(bookMarkUrlGroups);

        // Run the test
        final List<BookMarkUrlGroupResponse> result = bookMarkUrlGroupManagerUnderTest.getAllGroupManager("emailId");

        // Verify the results
    }
}
