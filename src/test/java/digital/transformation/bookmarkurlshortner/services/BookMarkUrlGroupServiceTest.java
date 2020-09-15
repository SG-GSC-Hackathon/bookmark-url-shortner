package digital.transformation.bookmarkurlshortner.services;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlGroupManager;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlGroupResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookMarkUrlGroupServiceTest {

    @Mock
    private BookMarkUrlGroupManager mockBookMarkUrlGroupManager;

    @InjectMocks
    private BookMarkUrlGroupService bookMarkUrlGroupServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
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

        // Configure BookMarkUrlGroupManager.createGroup(...).
        final BookMarkUrlGroupResponse bookMarkUrlGroupResponse = new BookMarkUrlGroupResponse();
        bookMarkUrlGroupResponse.setId(0);
        bookMarkUrlGroupResponse.setName("name");
        bookMarkUrlGroupResponse.setDescription("description");
        bookMarkUrlGroupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setCreated_by("created_by");
        bookMarkUrlGroupResponse.setTribe("tribe");
        bookMarkUrlGroupResponse.setTeam("team");
        bookMarkUrlGroupResponse.setComponent("component");
        bookMarkUrlGroupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setUpdated_by("updated_by");
        when(mockBookMarkUrlGroupManager.createGroup(any(BookMarkUrlGroupRequest.class))).thenReturn(bookMarkUrlGroupResponse);

        // Run the test
        final ResponseEntity result = bookMarkUrlGroupServiceUnderTest.createGroup(bookMarkUrlGroupRequest);

        // Verify the results
    }

    @Test
    public void testCreateGroup_ThrowsBookMarkUrlDigitalOrgException() {
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

        // Configure BookMarkUrlGroupManager.createGroup(...).
        final BookMarkUrlGroupResponse bookMarkUrlGroupResponse = new BookMarkUrlGroupResponse();
        bookMarkUrlGroupResponse.setId(0);
        bookMarkUrlGroupResponse.setName("name");
        bookMarkUrlGroupResponse.setDescription("description");
        bookMarkUrlGroupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setCreated_by("created_by");
        bookMarkUrlGroupResponse.setTribe("tribe");
        bookMarkUrlGroupResponse.setTeam("team");
        bookMarkUrlGroupResponse.setComponent("component");
        bookMarkUrlGroupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setUpdated_by("updated_by");
        when(mockBookMarkUrlGroupManager.createGroup(any(BookMarkUrlGroupRequest.class))).thenReturn(bookMarkUrlGroupResponse);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlGroupServiceUnderTest.createGroup(bookMarkUrlGroupRequest);
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
    }

    @Test
    public void testGetGroupbyId() {
        // Setup

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        final BookMarkUrlGroup result = bookMarkUrlGroupServiceUnderTest.getGroupbyId(0);

        // Verify the results
    }

    @Test
    public void testGetGroupbyId_ThrowsBookMarkUrlDigitalOrgException() {
        // Setup

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlGroupServiceUnderTest.getGroupbyId(0);
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
    }

    @Test
    public void testUpdateGroup() {
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

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Configure BookMarkUrlGroupManager.createGroup(...).
        final BookMarkUrlGroupResponse bookMarkUrlGroupResponse = new BookMarkUrlGroupResponse();
        bookMarkUrlGroupResponse.setId(0);
        bookMarkUrlGroupResponse.setName("name");
        bookMarkUrlGroupResponse.setDescription("description");
        bookMarkUrlGroupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setCreated_by("created_by");
        bookMarkUrlGroupResponse.setTribe("tribe");
        bookMarkUrlGroupResponse.setTeam("team");
        bookMarkUrlGroupResponse.setComponent("component");
        bookMarkUrlGroupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setUpdated_by("updated_by");
        when(mockBookMarkUrlGroupManager.createGroup(any(BookMarkUrlGroupRequest.class))).thenReturn(bookMarkUrlGroupResponse);

        // Run the test
        final ResponseEntity result = bookMarkUrlGroupServiceUnderTest.updateGroup(bookMarkUrlGroupRequest, 0);

        // Verify the results
    }

    @Test
    public void testUpdateGroup_ThrowsBookMarkUrlDigitalOrgException() {
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

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Configure BookMarkUrlGroupManager.createGroup(...).
        final BookMarkUrlGroupResponse bookMarkUrlGroupResponse = new BookMarkUrlGroupResponse();
        bookMarkUrlGroupResponse.setId(0);
        bookMarkUrlGroupResponse.setName("name");
        bookMarkUrlGroupResponse.setDescription("description");
        bookMarkUrlGroupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setCreated_by("created_by");
        bookMarkUrlGroupResponse.setTribe("tribe");
        bookMarkUrlGroupResponse.setTeam("team");
        bookMarkUrlGroupResponse.setComponent("component");
        bookMarkUrlGroupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setUpdated_by("updated_by");
        when(mockBookMarkUrlGroupManager.createGroup(any(BookMarkUrlGroupRequest.class))).thenReturn(bookMarkUrlGroupResponse);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlGroupServiceUnderTest.updateGroup(bookMarkUrlGroupRequest, 0);
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
    }

    @Test
    public void testDeleteGroup() {
        // Setup

        // Run the test
        bookMarkUrlGroupServiceUnderTest.deleteGroup(0);

        // Verify the results
        verify(mockBookMarkUrlGroupManager).deleteGroup(0);
    }

    @Test
    public void testDeleteGroup_ThrowsBookMarkUrlDigitalOrgException() {
        // Setup

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlGroupServiceUnderTest.deleteGroup(0);
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
        verify(mockBookMarkUrlGroupManager).deleteGroup(0);
    }

    @Test
    public void testAddUserToGroup() {
        // Setup

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        final ResponseEntity result = bookMarkUrlGroupServiceUnderTest.addUserToGroup("user", "admin", 0);

        // Verify the results
        verify(mockBookMarkUrlGroupManager).addUserToGroup("user", "groupName", "admin");
    }

    @Test
    public void testAddUserToGroup_ThrowsBookMarkUrlDigitalOrgException() {
        // Setup

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlGroupServiceUnderTest.addUserToGroup("user", "admin", 0);
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
        verify(mockBookMarkUrlGroupManager).addUserToGroup("user", "groupName", "admin");
    }

    @Test
    public void testRemoveUserFromGroup() {
        // Setup

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        final ResponseEntity result = bookMarkUrlGroupServiceUnderTest.removeUserFromGroup("user", "admin", 0);

        // Verify the results
        verify(mockBookMarkUrlGroupManager).removeUserToGroup("user", "groupName", "admin");
    }

    @Test
    public void testRemoveUserFromGroup_ThrowsBookMarkUrlDigitalOrgException() {
        // Setup

        // Configure BookMarkUrlGroupManager.getGroupById(...).
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
        when(mockBookMarkUrlGroupManager.getGroupById(0)).thenReturn(bookMarkUrlGroup);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlGroupServiceUnderTest.removeUserFromGroup("user", "admin", 0);
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
        verify(mockBookMarkUrlGroupManager).removeUserToGroup("user", "groupName", "admin");
    }

    @Test
    public void testGetAllGroupService() {
        // Setup

        // Configure BookMarkUrlGroupManager.getAllGroupManager(...).
        final BookMarkUrlGroupResponse bookMarkUrlGroupResponse = new BookMarkUrlGroupResponse();
        bookMarkUrlGroupResponse.setId(0);
        bookMarkUrlGroupResponse.setName("name");
        bookMarkUrlGroupResponse.setDescription("description");
        bookMarkUrlGroupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setCreated_by("created_by");
        bookMarkUrlGroupResponse.setTribe("tribe");
        bookMarkUrlGroupResponse.setTeam("team");
        bookMarkUrlGroupResponse.setComponent("component");
        bookMarkUrlGroupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setUpdated_by("updated_by");
        final List<BookMarkUrlGroupResponse> bookMarkUrlGroupResponses = Arrays.asList(bookMarkUrlGroupResponse);
        when(mockBookMarkUrlGroupManager.getAllGroupManager("emailId")).thenReturn(bookMarkUrlGroupResponses);

        // Run the test
        final ResponseEntity<List> result = bookMarkUrlGroupServiceUnderTest.getAllGroupService("email");

        // Verify the results
    }

    @Test
    public void testGetAllGroupService_ThrowsBookMarkUrlDigitalOrgException() {
        // Setup

        // Configure BookMarkUrlGroupManager.getAllGroupManager(...).
        final BookMarkUrlGroupResponse bookMarkUrlGroupResponse = new BookMarkUrlGroupResponse();
        bookMarkUrlGroupResponse.setId(0);
        bookMarkUrlGroupResponse.setName("name");
        bookMarkUrlGroupResponse.setDescription("description");
        bookMarkUrlGroupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setCreated_by("created_by");
        bookMarkUrlGroupResponse.setTribe("tribe");
        bookMarkUrlGroupResponse.setTeam("team");
        bookMarkUrlGroupResponse.setComponent("component");
        bookMarkUrlGroupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlGroupResponse.setUpdated_by("updated_by");
        final List<BookMarkUrlGroupResponse> bookMarkUrlGroupResponses = Arrays.asList(bookMarkUrlGroupResponse);
        when(mockBookMarkUrlGroupManager.getAllGroupManager("emailId")).thenReturn(bookMarkUrlGroupResponses);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlGroupServiceUnderTest.getAllGroupService("email");
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
    }
}
