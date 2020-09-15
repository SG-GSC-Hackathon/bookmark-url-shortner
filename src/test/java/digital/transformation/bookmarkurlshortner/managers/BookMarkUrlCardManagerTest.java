package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlCardRepository;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlIconRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookMarkUrlCardManagerTest {

    @Mock
    private BookMarkUrlCardRepository mockBookMarkUrlCardRepository;
    @Mock
    private BookMarkUrlIconRepository mockBookMarkUrlIconRepository;

    @InjectMocks
    private BookMarkUrlCardManager bookMarkUrlCardManagerUnderTest;

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

        // Configure BookMarkUrlCardRepository.save(...).
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
        when(mockBookMarkUrlCardRepository.save(any(BookMarkUrlCard.class))).thenReturn(bookMarkUrlCard);

        // Run the test
        final BookMarkUrlCardResponse result = bookMarkUrlCardManagerUnderTest.createCard(bookMarkUrlCardRequest);

        // Verify the results
    }

    @Test
    public void testCreateCard_ThrowsIOException() {
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

        // Configure BookMarkUrlCardRepository.save(...).
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
        when(mockBookMarkUrlCardRepository.save(any(BookMarkUrlCard.class))).thenReturn(bookMarkUrlCard);

        // Run the test
        assertThatThrownBy(() -> {
            bookMarkUrlCardManagerUnderTest.createCard(bookMarkUrlCardRequest);
        }).isInstanceOf(IOException.class);
    }

    @Test
    public void testGetCardById() {
        // Setup

        // Configure BookMarkUrlCardRepository.findById(...).
        final BookMarkUrlCard bookMarkUrlCard1 = new BookMarkUrlCard();
        bookMarkUrlCard1.setId(0);
        bookMarkUrlCard1.setTitle("title");
        bookMarkUrlCard1.setDescription("description");
        bookMarkUrlCard1.setOriginal_url("original_url");
        bookMarkUrlCard1.setShort_url("short_url");
        bookMarkUrlCard1.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCard1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        bookMarkUrlCard1.setCreated_by("created_by");
        bookMarkUrlCard1.setTribe("tribe");
        bookMarkUrlCard1.setTeam("team");
        final Optional<BookMarkUrlCard> bookMarkUrlCard = Optional.of(bookMarkUrlCard1);
        when(mockBookMarkUrlCardRepository.findById(0)).thenReturn(bookMarkUrlCard);

        // Run the test
        final BookMarkUrlCard result = bookMarkUrlCardManagerUnderTest.getCardById(0);

        // Verify the results
    }

    @Test
    public void testUplaodImage() {
        // Setup
        final BookMarkUrlIcon bookMarkUrlIcon = new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0);

        // Configure BookMarkUrlIconRepository.save(...).
        final BookMarkUrlIcon bookMarkUrlIcon1 = new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0);
        when(mockBookMarkUrlIconRepository.save(any(BookMarkUrlIcon.class))).thenReturn(bookMarkUrlIcon1);

        // Run the test
        bookMarkUrlCardManagerUnderTest.uplaodImage(bookMarkUrlIcon);

        // Verify the results
    }

    @Test
    public void testDownloadImage() {
        // Setup

        // Configure BookMarkUrlIconRepository.findById(...).
        final Optional<BookMarkUrlIcon> bookMarkUrlIcon = Optional.of(new BookMarkUrlIcon("originalFilename", "contentType", "content".getBytes(), 0));
        when(mockBookMarkUrlIconRepository.findById(0)).thenReturn(bookMarkUrlIcon);

        // Run the test
        final BookMarkUrlIcon result = bookMarkUrlCardManagerUnderTest.downloadImage(0);

        // Verify the results
    }

    @Test
    public void testDeleteCard() {
        // Setup

        // Run the test
        bookMarkUrlCardManagerUnderTest.deleteCard(0);

        // Verify the results
        verify(mockBookMarkUrlCardRepository).deleteById(0);
    }

    @Test
    public void testGetAllCard() {
        // Setup

        // Configure BookMarkUrlCardRepository.findAll(...).
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
        final List<BookMarkUrlCard> bookMarkUrlCards = Arrays.asList(bookMarkUrlCard);
        when(mockBookMarkUrlCardRepository.findAll()).thenReturn(bookMarkUrlCards);

        // Run the test
        final List<BookMarkUrlCardResponse> result = bookMarkUrlCardManagerUnderTest.getAllCard("emailId");

        // Verify the results
    }
}
