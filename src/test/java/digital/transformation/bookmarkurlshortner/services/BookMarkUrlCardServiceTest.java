package digital.transformation.bookmarkurlshortner.services;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlCardManager;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlIconResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookMarkUrlCardServiceTest {

    @Mock
    private BookMarkUrlCardManager mockBookMarkUrlCardManager;

    @InjectMocks
    private BookMarkUrlCardService bookMarkUrlCardServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testCreateCard() throws Exception {
        // Setup
        final BookMarkUrlCardRequest bookMarkUrlCardRequest = new BookMarkUrlCardRequest();
        bookMarkUrlCardRequest.setTitle("title");
        bookMarkUrlCardRequest.setDescription("description");
        bookMarkUrlCardRequest.setOriginal_url("original_url");
        bookMarkUrlCardRequest.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardRequest.setCreated_by("created_by");
        bookMarkUrlCardRequest.setTribe("tribe");
        bookMarkUrlCardRequest.setTeam("team");
        bookMarkUrlCardRequest.setComponent("component");
        bookMarkUrlCardRequest.setUpdated_by("updated_by");
        bookMarkUrlCardRequest.setGroup_name("group_name");

        // Configure BookMarkUrlCardManager.createCard(...).
        final BookMarkUrlCardResponse bookMarkUrlCardResponse = new BookMarkUrlCardResponse();
        bookMarkUrlCardResponse.setId(0);
        bookMarkUrlCardResponse.setTitle("title");
        bookMarkUrlCardResponse.setDescription("description");
        bookMarkUrlCardResponse.setOriginal_url("original_url");
        bookMarkUrlCardResponse.setShort_url("short_url");
        bookMarkUrlCardResponse.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardResponse.setCreated_by("created_by");
        bookMarkUrlCardResponse.setTribe("tribe");
        bookMarkUrlCardResponse.setTeam("team");
        when(mockBookMarkUrlCardManager.createCard(any(BookMarkUrlCardRequest.class))).thenReturn(bookMarkUrlCardResponse);

        // Run the test
        final ResponseEntity<BookMarkUrlCardResponse> result = bookMarkUrlCardServiceUnderTest.createCard(bookMarkUrlCardRequest);

        // Verify the results
    }

    @Test
    public void testCreateCard_BookMarkUrlCardManagerThrowsIOException() throws Exception {
        // Setup
        final BookMarkUrlCardRequest bookMarkUrlCardRequest = new BookMarkUrlCardRequest();
        bookMarkUrlCardRequest.setTitle("title");
        bookMarkUrlCardRequest.setDescription("description");
        bookMarkUrlCardRequest.setOriginal_url("original_url");
        bookMarkUrlCardRequest.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardRequest.setCreated_by("created_by");
        bookMarkUrlCardRequest.setTribe("tribe");
        bookMarkUrlCardRequest.setTeam("team");
        bookMarkUrlCardRequest.setComponent("component");
        bookMarkUrlCardRequest.setUpdated_by("updated_by");
        bookMarkUrlCardRequest.setGroup_name("group_name");

        when(mockBookMarkUrlCardManager.createCard(any(BookMarkUrlCardRequest.class))).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlCardServiceUnderTest.createCard(bookMarkUrlCardRequest);
        }).isInstanceOf(IOException.class);
    }

    @Test
    public void testUplaodImage() throws Exception {
        // Setup
        final MultipartFile file = null;

        // Run the test
        final ResponseEntity result = bookMarkUrlCardServiceUnderTest.uplaodImage(0, file);

        // Verify the results
        verify(mockBookMarkUrlCardManager).uplaodImage(any(BookMarkUrlIcon.class));
    }

    @Test
    public void testUplaodImage_ThrowsIOException() {
        // Setup
        final MultipartFile file = null;

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlCardServiceUnderTest.uplaodImage(0, file);
        }).isInstanceOf(IOException.class);
        verify(mockBookMarkUrlCardManager).uplaodImage(any(BookMarkUrlIcon.class));
    }

    @Test
    public void testDownloadImage() throws Exception {
        // Setup

        // Configure BookMarkUrlCardManager.downloadImage(...).
        final BookMarkUrlIcon bookMarkUrlIcon = new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0);
        when(mockBookMarkUrlCardManager.downloadImage(0)).thenReturn(bookMarkUrlIcon);

        // Run the test
        final ResponseEntity<BookMarkUrlIconResponse> result = bookMarkUrlCardServiceUnderTest.downloadImage(0);

        // Verify the results
    }

    @Test
    public void testDownloadImage_ThrowsIOException() {
        // Setup

        // Configure BookMarkUrlCardManager.downloadImage(...).
        final BookMarkUrlIcon bookMarkUrlIcon = new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0);
        when(mockBookMarkUrlCardManager.downloadImage(0)).thenReturn(bookMarkUrlIcon);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlCardServiceUnderTest.downloadImage(0);
        }).isInstanceOf(IOException.class);
    }

    @Test
    public void testDownloadImageocta() throws Exception {
        // Setup

        // Configure BookMarkUrlCardManager.downloadImage(...).
        final BookMarkUrlIcon bookMarkUrlIcon = new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0);
        when(mockBookMarkUrlCardManager.downloadImage(0)).thenReturn(bookMarkUrlIcon);

        // Run the test
        final ResponseEntity<Resource> result = bookMarkUrlCardServiceUnderTest.downloadImageocta(0);

        // Verify the results
    }

    @Test
    public void testDownloadImageocta_ThrowsIOException() {
        // Setup

        // Configure BookMarkUrlCardManager.downloadImage(...).
        final BookMarkUrlIcon bookMarkUrlIcon = new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0);
        when(mockBookMarkUrlCardManager.downloadImage(0)).thenReturn(bookMarkUrlIcon);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlCardServiceUnderTest.downloadImageocta(0);
        }).isInstanceOf(IOException.class);
    }

    @Test
    public void testDeleteCard() {
        // Setup

        // Configure BookMarkUrlCardManager.getCardById(...).
        final BookMarkUrlCard bookMarkUrlCard = new BookMarkUrlCard();
        bookMarkUrlCard.setId(0);
        bookMarkUrlCard.setTitle("title");
        bookMarkUrlCard.setDescription("description");
        bookMarkUrlCard.setOriginal_url("original_url");
        bookMarkUrlCard.setShort_url("short_url");
        bookMarkUrlCard.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCard.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCard.setCreated_by("created_by");
        bookMarkUrlCard.setTribe("tribe");
        bookMarkUrlCard.setTeam("team");
        when(mockBookMarkUrlCardManager.getCardById(0)).thenReturn(bookMarkUrlCard);

        // Run the test
        final ResponseEntity result = bookMarkUrlCardServiceUnderTest.deleteCard(0, "email");

        // Verify the results
        verify(mockBookMarkUrlCardManager).deleteCard(0);
    }

    @Test
    public void testDeleteCard_ThrowsBookMarkUrlDigitalOrgException() {
        // Setup

        // Configure BookMarkUrlCardManager.getCardById(...).
        final BookMarkUrlCard bookMarkUrlCard = new BookMarkUrlCard();
        bookMarkUrlCard.setId(0);
        bookMarkUrlCard.setTitle("title");
        bookMarkUrlCard.setDescription("description");
        bookMarkUrlCard.setOriginal_url("original_url");
        bookMarkUrlCard.setShort_url("short_url");
        bookMarkUrlCard.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCard.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCard.setCreated_by("created_by");
        bookMarkUrlCard.setTribe("tribe");
        bookMarkUrlCard.setTeam("team");
        when(mockBookMarkUrlCardManager.getCardById(0)).thenReturn(bookMarkUrlCard);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlCardServiceUnderTest.deleteCard(0, "email");
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
        verify(mockBookMarkUrlCardManager).deleteCard(0);
    }

    @Test
    public void testGetAllcard() {
        // Setup

        // Configure BookMarkUrlCardManager.getAllCard(...).
        final BookMarkUrlCardResponse bookMarkUrlCardResponse = new BookMarkUrlCardResponse();
        bookMarkUrlCardResponse.setId(0);
        bookMarkUrlCardResponse.setTitle("title");
        bookMarkUrlCardResponse.setDescription("description");
        bookMarkUrlCardResponse.setOriginal_url("original_url");
        bookMarkUrlCardResponse.setShort_url("short_url");
        bookMarkUrlCardResponse.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardResponse.setCreated_by("created_by");
        bookMarkUrlCardResponse.setTribe("tribe");
        bookMarkUrlCardResponse.setTeam("team");
        final List<BookMarkUrlCardResponse> bookMarkUrlCardResponses = Arrays.asList(bookMarkUrlCardResponse);
        when(mockBookMarkUrlCardManager.getAllCard("emailId")).thenReturn(bookMarkUrlCardResponses);

        // Run the test
        final ResponseEntity<List> result = bookMarkUrlCardServiceUnderTest.getAllcard("email");

        // Verify the results
    }

    @Test
    public void testGetAllcard_ThrowsBookMarkUrlDigitalOrgException() {
        // Setup

        // Configure BookMarkUrlCardManager.getAllCard(...).
        final BookMarkUrlCardResponse bookMarkUrlCardResponse = new BookMarkUrlCardResponse();
        bookMarkUrlCardResponse.setId(0);
        bookMarkUrlCardResponse.setTitle("title");
        bookMarkUrlCardResponse.setDescription("description");
        bookMarkUrlCardResponse.setOriginal_url("original_url");
        bookMarkUrlCardResponse.setShort_url("short_url");
        bookMarkUrlCardResponse.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCardResponse.setCreated_by("created_by");
        bookMarkUrlCardResponse.setTribe("tribe");
        bookMarkUrlCardResponse.setTeam("team");
        final List<BookMarkUrlCardResponse> bookMarkUrlCardResponses = Arrays.asList(bookMarkUrlCardResponse);
        when(mockBookMarkUrlCardManager.getAllCard("emailId")).thenReturn(bookMarkUrlCardResponses);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlCardServiceUnderTest.getAllcard("email");
        }).isInstanceOf(BookMarkUrlDigitalOrgException.class);
    }
}
