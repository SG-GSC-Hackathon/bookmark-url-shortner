package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlNotFoundException;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUrl;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlCardRepository;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlUrlRepository;
import digital.transformation.bookmarkurlshortner.util.BookMarkUrlBaseConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type BookMarkUrlUrl manager.
 */
@Component
public class BookMarkUrlUrlManager {

    @Autowired
    private BookMarkUrlUrlRepository bookMarkUrlUrlRepository;

    @Autowired
    private BookMarkUrlCardRepository bookMarkUrlCardRepository;

    /**
     * The Base conversion.
     */
    @Autowired
    BookMarkUrlBaseConversion bookMarkUrlBaseConversion;


    /**
     * Gets original url.
     *
     * @param shortUrl the short url
     * @return the original url
     */
    public String getOriginalUrl(String shortUrl) {
        Optional<BookMarkUrlUrl> entity;
        try {
            int id = bookMarkUrlBaseConversion.decode(shortUrl);

            Optional<BookMarkUrlCard> card = bookMarkUrlCardRepository.findById(id);
            entity = bookMarkUrlUrlRepository.findById(card.get().getUrl_id());
            if (entity.get() == null) {
                throw new BookMarkUrlNotFoundException("There is no record with " + shortUrl);
            }
            if (entity.get().getExpires_date() != null && entity.get().getExpires_date().before(new Date())) {
//                bookMarkUrlUrlRepository.delete(entity.get());
                throw new BookMarkUrlNotFoundException("Link expired!");
            }
        } catch (NoSuchElementException exception) {
            throw new BookMarkUrlNotFoundException("There is no record with " + shortUrl);
        }
        return entity.get().getLong_url();
    }
}
