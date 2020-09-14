package digital.transformation.bookmarkurlshortner.api;

import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlCardManager;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlCardRepository;
import digital.transformation.bookmarkurlshortner.services.BookMarkUrlCardService;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

public class BookMarkUrlCardApiTest {

    @InjectMocks
    private BookMarkUrlCardService cardService;

    @Mock
    private BookMarkUrlCardManager bookMarkUrlCardManager;

    @Mock
    private BookMarkUrlCardRepository bookMarkUrlCardRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
    public void getAllCardsTest() throws Exception {
        BookMarkUrlCardRequest bookMarkUrlCardRequest = new BookMarkUrlCardRequest();
        bookMarkUrlCardRequest.setCreated_by("test@gmail.com");
        bookMarkUrlCardRequest.setUpdated_by("test@gmail.com");
        ResponseEntity<BookMarkUrlCardResponse> cardList = new ResponseEntity<>(HttpStatus.CREATED);
        Mockito.when(cardService.createCard(bookMarkUrlCardRequest)).thenReturn(cardList);
        List<BookMarkUrlCardResponse> cards = bookMarkUrlCardManager.getAllCard();
        Mockito.verify(bookMarkUrlCardManager).getAllCard();
        Assert.assertEquals(cards, cardList);

    }
}
