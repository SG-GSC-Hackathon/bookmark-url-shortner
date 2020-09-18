package digital.transformation.bookmarkurlshortner.managers;


import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlCardRepository;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlIconRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * The type Card manager test.
 */
public class BookMarkUrlCardManagerTest {

    @Mock
    private BookMarkUrlCardRepository mockCardRepository;
    @Mock
    private BookMarkUrlIconRepository mockIconRepository;

    @InjectMocks
    private BookMarkUrlCardManager cardManagerUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    /**
     * Test create card.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCreateCard() throws Exception {
        // Setup
        final BookMarkUrlCardRequest cardRequest = new BookMarkUrlCardRequest();
        cardRequest.setTitle("title");
        cardRequest.setDescription("description");
        cardRequest.setOriginal_url("original_url");
        cardRequest.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        cardRequest.setCreated_by("created_by");
        cardRequest.setTribe("tribe");
        cardRequest.setTeam("team");
        cardRequest.setComponent("component");
        cardRequest.setUpdated_by("updated_by");

        // Configure CardRepository.save(...).
        final BookMarkUrlCard card = new BookMarkUrlCard();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(23);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        when(mockCardRepository.save(any(BookMarkUrlCard.class))).thenReturn(card);

        // Run the test
        final BookMarkUrlCardResponse result = cardManagerUnderTest.createCard(cardRequest);

        // Verify the results
    }

    /**
     * Test create card throws io exception.
     *
     * @throws Exception the exception
     */
    @Test(expectedExceptions = {IOException.class})
    public void testCreateCard_ThrowsIOException() throws Exception {
        // Setup
        final BookMarkUrlCardRequest cardRequest = new BookMarkUrlCardRequest();
        cardRequest.setTitle("title");
        cardRequest.setDescription("description");
        cardRequest.setOriginal_url("original_url");
        cardRequest.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        cardRequest.setCreated_by("created_by");
        cardRequest.setTribe("tribe");
        cardRequest.setTeam("team");
        cardRequest.setComponent("component");
        cardRequest.setUpdated_by("updated_by");

        // Configure CardRepository.save(...).
        final BookMarkUrlCard card = new BookMarkUrlCard();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(3);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        when(mockCardRepository.save(any(BookMarkUrlCard.class))).thenReturn(card);

        // Run the test
        cardManagerUnderTest.createCard(cardRequest);
    }

    /**
     * Test get card by id.
     */
    @Test
    public void testGetCardById() {
        // Setup

        // Configure CardRepository.findById(...).
        final BookMarkUrlCard card1 = new BookMarkUrlCard();
        card1.setId(0);
        card1.setTitle("title");
        card1.setDescription("description");
        card1.setUrl_id(90);
        card1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card1.setCreated_by("created_by");
        card1.setTribe("tribe");
        card1.setTeam("team");
        final Optional<BookMarkUrlCard> card = Optional.of(card1);
        when(mockCardRepository.findById(0)).thenReturn(card);

        // Run the test
        final BookMarkUrlCard result = cardManagerUnderTest.getCardById(0);

        // Verify the results
    }

    /**
     * Test uplaod image.
     */
    @Test
    public void testUplaodImage() {
        // Setup
        final BookMarkUrlIcon icon = new BookMarkUrlIcon("originalFilename", "contentType", 0, "content".getBytes());

        // Configure IconRepository.save(...).
        final BookMarkUrlIcon icon1 = new BookMarkUrlIcon("originalFilename", "contentType", 0, "content".getBytes());
        when(mockIconRepository.save(any(BookMarkUrlIcon.class))).thenReturn(icon1);

        // Run the test
        cardManagerUnderTest.uplaodImage(icon);

        // Verify the results
    }

    /**
     * Test download image.
     */
    @Test
    public void testDownloadImage() {
        // Setup

        // Configure IconRepository.findById(...).
        final Optional<BookMarkUrlIcon> icon = Optional.of(new BookMarkUrlIcon("originalFilename", "contentType", 0, "content".getBytes()));
        when(mockIconRepository.findById(0)).thenReturn(icon);

        // Run the test
        final BookMarkUrlIcon result = cardManagerUnderTest.downloadImage(0);

        // Verify the results
    }

    /**
     * Test delete card.
     */
    @Test
    public void testDeleteCard() {
        // Setup

        final BookMarkUrlCard card = new BookMarkUrlCard();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(45);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        // Run the test
        cardManagerUnderTest.deleteCard(card, "email");

        // Verify the results
        verify(mockCardRepository).deleteById(0);
    }

    /**
     * Test get all card.
     */
    @Test
    public void testGetAllCard() {
        // Setup

        // Configure CardRepository.findAll(...).
        final BookMarkUrlCard card = new BookMarkUrlCard();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(45);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        final List<BookMarkUrlCard> cards = Arrays.asList(card);
        when(mockCardRepository.findAll()).thenReturn(cards);

        // Run the test
        final List<BookMarkUrlCardResponse> result = cardManagerUnderTest.getAllCard("emailId");

        // Verify the results
    }
}
