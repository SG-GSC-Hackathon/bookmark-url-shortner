package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlBadRequestException;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlSuggestionQueue;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUrl;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardUpdateRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlSuggestionQueueRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.repository.*;
import digital.transformation.bookmarkurlshortner.util.BookMarkUrlBaseConversion;
import digital.transformation.bookmarkurlshortner.util.BookMarkUrlDigitalUtil;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type BookMarkUrlCard manager.
 */
@Component
public class BookMarkUrlCardManager {

    @Autowired
    private BookMarkUrlCardRepository bookMarkUrlCardRepository;

    @Autowired
    private BookMarkUrlUrlRepository bookMarkUrlUrlRepository;

    @Autowired
    private BookMarkUrlCardInGroupRepository bookMarkUrlCardInGroupRepository;

    /**
     * The BookMarkUrlIcon repository.
     */
    @Autowired
    BookMarkUrlIconRepository bookMarkUrlIconRepository;

    /**
     * The Base conversion.
     */
    @Autowired
    BookMarkUrlBaseConversion bookMarkUrlBaseConversion;

    /**
     * The BookMarkUrlGroup custom repository.
     */
    @Autowired
    BookMarkUrlGroupCustomRepository bookMarkUrlGroupCustomRepository;

    /**
     * The Suggestion queue repository.
     */
    @Autowired
    BookMarkUrlSuggestionQueueRepository bookMarkUrlSuggestionQueueRepository;

    /**
     * Create short url url.
     *
     * @param originalUrl the original url
     * @param date        the date
     * @param cardId      the card id
     * @return the url
     */
    public BookMarkUrlUrl createShortUrl(String originalUrl, Date date, int cardId) {
        BookMarkUrlUrl bookMarkUrlUrl = new BookMarkUrlUrl();
        bookMarkUrlUrl.setLong_url(originalUrl);
        bookMarkUrlUrl.setExpires_date(date);
        bookMarkUrlUrl.setCreated_date(new Date());
        bookMarkUrlUrl.setCard_id(cardId);
        bookMarkUrlUrl.setShort_url(bookMarkUrlBaseConversion.encode(cardId));
        BookMarkUrlUrl newBookMarkUrlUrl = bookMarkUrlUrlRepository.save(bookMarkUrlUrl);
        return newBookMarkUrlUrl;
    }


    /**
     * Create card card response.
     *
     * @param bookMarkUrlCardRequest the card request
     * @return the card response
     * @throws IOException the io exception
     */
    public BookMarkUrlCardResponse createCard(BookMarkUrlCardRequest bookMarkUrlCardRequest) throws IOException {
        cardRequestValidation(bookMarkUrlCardRequest);
        ModelMapper reqModelMapper = new ModelMapper();
        BookMarkUrlCard bookMarkUrlCard = reqModelMapper.map(bookMarkUrlCardRequest, BookMarkUrlCard.class);
        bookMarkUrlCard.setCreated_date(new Date());
        bookMarkUrlCard.setUpdated_date(new Date());
        bookMarkUrlCard.setActive(true);

        BookMarkUrlCard response = bookMarkUrlCardRepository.save(bookMarkUrlCard);

        ModelMapper resModelMapper = new ModelMapper();
        BookMarkUrlCardResponse bookMarkUrlCardResponse = resModelMapper.map(response, BookMarkUrlCardResponse.class);

        ClassPathResource classPathResource = new ClassPathResource("img/favicon.png");
        byte[] bytes = new byte[(int) classPathResource.contentLength()];
        BookMarkUrlIcon bookMarkUrlIcon = bookMarkUrlIconRepository.save(new BookMarkUrlIcon("favicon.png", "image/png", response.getId(), BookMarkUrlDigitalUtil.compressBytes(bytes)));

        if(!bookMarkUrlCardInGroupRepository.fetchGrupByCardId(response.getId()).isEmpty()) {
            bookMarkUrlCardRequest.setExpire_date(null);
        }

        if (bookMarkUrlCardRequest.getOriginal_url() != null) {
            BookMarkUrlUrl newBookMarkUrlUrl = createShortUrl(bookMarkUrlCardRequest.getOriginal_url(), bookMarkUrlCardRequest.getExpire_date(), response.getId());
            bookMarkUrlCardRepository.updateAddress(newBookMarkUrlUrl.getId(), bookMarkUrlIcon.getId(), response.getId());
            bookMarkUrlCardResponse.setOriginal_url(newBookMarkUrlUrl.getLong_url());
            bookMarkUrlCardResponse.setShort_url(newBookMarkUrlUrl.getShort_url());
            bookMarkUrlCardResponse.setExpire_date(newBookMarkUrlUrl.getCreated_date());
        }
        bookMarkUrlCardResponse.setHasAdmin(true);
        return bookMarkUrlCardResponse;

    }

    /**
     * BookMarkUrlCard request validation.
     *
     * @param bookMarkUrlCardRequest the card request
     */
    public void cardRequestValidation(BookMarkUrlCardRequest bookMarkUrlCardRequest) {
        if (!BookMarkUrlDigitalUtil.isEmailValid(bookMarkUrlCardRequest.getCreated_by()) || !BookMarkUrlDigitalUtil.isEmailValid(bookMarkUrlCardRequest.getUpdated_by())) {
            throw new BookMarkUrlBadRequestException("Email is not valid");
        }
        if (!BookMarkUrlDigitalUtil.isUrlValid(bookMarkUrlCardRequest.getOriginal_url())) {
            throw new BookMarkUrlBadRequestException("Original BookMarkUrlUrl is not valid");
        }
    }

    /**
     * Gets card by id.
     *
     * @param cardId the card id
     * @return the card by id
     */
    public BookMarkUrlCard getCardById(int cardId) {
        Optional<BookMarkUrlCard> card = bookMarkUrlCardRepository.findById(cardId);
        if(card.isPresent()) {
            return card.get();
        }
        else {
            return new BookMarkUrlCard();
        }
    }

    /**
     * Uplaod image.
     *
     * @param bookMarkUrlIcon the bookMarkUrlIcon
     */
    public void uplaodImage(BookMarkUrlIcon bookMarkUrlIcon) {
        bookMarkUrlIconRepository.save(bookMarkUrlIcon);
    }

    /**
     * Download image icon.
     *
     * @param cardId the card id
     * @return the icon
     */
    public BookMarkUrlIcon downloadImage(int cardId) {
        Optional<BookMarkUrlIcon> icon = bookMarkUrlIconRepository.findById(getCardById(cardId).getIcon_id());
        if(icon.isPresent()) {
            return icon.get();
        }
        else {
            return new BookMarkUrlIcon();
        }
    }

    /**
     * Delete bookMarkUrlCard.
     *
     * @param bookMarkUrlCard  the bookMarkUrlCard
     * @param email the email
     */
    public void deleteCard(BookMarkUrlCard bookMarkUrlCard, String email) {
        if (bookMarkUrlCard.getCreated_by().equals(email)) {
            bookMarkUrlSuggestionQueueRepository.deleteSuggestionQueueByCardId(bookMarkUrlCard.getId());
            bookMarkUrlCardInGroupRepository.deleteCardInGroup(bookMarkUrlCard.getId());
            bookMarkUrlIconRepository.deleteById(bookMarkUrlCard.getIcon_id());
            bookMarkUrlUrlRepository.deleteById(bookMarkUrlCard.getUrl_id());
            bookMarkUrlCardRepository.deleteById(bookMarkUrlCard.getId());
        } else {
            throw new BookMarkUrlDigitalOrgException("You are not an admin of this group.");
        }

    }


    /**
     * Gets all card.
     *
     * @param emailId the email id
     * @return the all card
     */
    public List<BookMarkUrlCardResponse> getAllCard(String emailId) {
        List<BookMarkUrlCardResponse> cardList = bookMarkUrlCardRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, BookMarkUrlCardResponse.class), Collectors.toList()));

        List<BookMarkUrlCardResponse> bookMarkUrlCardResponseList = cardList.stream().map(bookMarkUrlCardResponse -> {

            Collection<BookMarkUrlUrl> list = bookMarkUrlUrlRepository.fetchURLObjectByCardId(bookMarkUrlCardResponse.getId());
            if(!list.isEmpty()) {
                BookMarkUrlUrl bookMarkUrlUrl = list.iterator().next();
                bookMarkUrlCardResponse.setOriginal_url(bookMarkUrlUrl.getLong_url());
                bookMarkUrlCardResponse.setShort_url(bookMarkUrlUrl.getShort_url());
                bookMarkUrlCardResponse.setExpire_date(bookMarkUrlUrl.getExpires_date());
            }
            if (bookMarkUrlCardResponse.getCreated_by().equals(emailId)) {
                bookMarkUrlCardResponse.setHasAdmin(true);
            } else {
                bookMarkUrlCardResponse.setHasAdmin(false);
            }
            return bookMarkUrlCardResponse;
        }).collect(Collectors.toList());
        return bookMarkUrlCardResponseList;
    }

    /**
     * Gets allcard for owner.
     *
     * @param emailId the email id
     * @return the allcard for owner
     */
    public List<BookMarkUrlCardResponse> getAllcardForOwner(String emailId) {
        List<BookMarkUrlCardResponse> bookMarkUrlCardResponseList = getAllCard(emailId);
        return bookMarkUrlCardResponseList.stream().filter(bookMarkUrlCardResponse -> bookMarkUrlCardResponse.getHasAdmin() == true).collect(Collectors.toList());
    }

    /**
     * Update card object.
     *
     * @param cardRequest the card request
     * @return the object
     */
    public BookMarkUrlCardResponse updateCard(BookMarkUrlCardUpdateRequest cardRequest) {
        BookMarkUrlCardResponse bookMarkUrlCardResponse = new BookMarkUrlCardResponse();
        Optional<BookMarkUrlCard> cardOptional = bookMarkUrlCardRepository.findById(cardRequest.getId());
        String shortUrl = null;
        BookMarkUrlCard bookMarkUrlCard = cardOptional.get();
        if (bookMarkUrlCard != null && bookMarkUrlCard.getCreated_by().equals(cardRequest.getUpdated_by())) {
            ModelMapper reqModelMapper = new ModelMapper();
            BookMarkUrlCard bookMarkUrlCardMapper = reqModelMapper.map(cardRequest, BookMarkUrlCard.class);
            bookMarkUrlCardMapper.setUpdated_date(new Date());
            bookMarkUrlCardMapper.setCreated_by(bookMarkUrlCard.getCreated_by());
            bookMarkUrlCardMapper.setCreated_date(bookMarkUrlCard.getCreated_date());
            bookMarkUrlCardMapper.setActive(bookMarkUrlCard.getActive());
            bookMarkUrlCardMapper.setIcon_id(bookMarkUrlCard.getIcon_id());
            bookMarkUrlCardMapper.setUrl_id(bookMarkUrlCard.getUrl_id());
            if (cardRequest.getOriginal_url() != null) {
                shortUrl = updateShortUrl(bookMarkUrlCard, cardRequest.getOriginal_url(), cardRequest.getExpire_date(), bookMarkUrlCard.getUrl_id());

            }

            if(!bookMarkUrlCardInGroupRepository.fetchGrupByCardId(bookMarkUrlCard.getId()).isEmpty()) {
                cardRequest.setExpire_date(null);
            }
            bookMarkUrlCardRepository.save(bookMarkUrlCardMapper);
            ModelMapper resModelMapper = new ModelMapper();
            bookMarkUrlCardResponse = resModelMapper.map(bookMarkUrlCardMapper, BookMarkUrlCardResponse.class);
            bookMarkUrlCardResponse.setHasAdmin(true);
            bookMarkUrlCardResponse.setOriginal_url(cardRequest.getOriginal_url());
            bookMarkUrlCardResponse.setShort_url(shortUrl);
            bookMarkUrlCardResponse.setExpire_date(cardRequest.getExpire_date());
            return bookMarkUrlCardResponse;
        }
        else {
            throw new BookMarkUrlBadRequestException("You are not the owner of this bookMarkUrlCard."+ cardRequest.getTitle());
        }
    }

    /**
     * Update short url.
     *
     * @param bookMarkUrlCard        the bookMarkUrlCard
     * @param originalUrl the original url
     * @param date        the date
     * @param urlId       the url id
     * @return the string
     */
    public String updateShortUrl(BookMarkUrlCard bookMarkUrlCard, String originalUrl, Date date, int urlId) {
        String shortUrl = bookMarkUrlBaseConversion.encode(bookMarkUrlCard.getId());
        bookMarkUrlUrlRepository.updateUrl(originalUrl, shortUrl, date, urlId);
        return shortUrl;
    }

    /**
     * Suggestion for card suggestion queue.
     *
     * @param bookMarkUrlSuggestionQueueRequest the suggestion queue request
     * @return the suggestion queue
     * @throws NotFoundException the not found exception
     */
    public BookMarkUrlSuggestionQueue suggestionForCard(BookMarkUrlSuggestionQueueRequest bookMarkUrlSuggestionQueueRequest) throws NotFoundException {
        Optional<BookMarkUrlCard> card = bookMarkUrlCardRepository.findById(bookMarkUrlSuggestionQueueRequest.getCard_id());
        if (card.isPresent() && card.get() != null) {
            BookMarkUrlSuggestionQueue bookMarkUrlSuggestionQueue = new BookMarkUrlSuggestionQueue();
            bookMarkUrlSuggestionQueue.setCard_id(bookMarkUrlSuggestionQueueRequest.getCard_id());
            bookMarkUrlSuggestionQueue.setEmail(bookMarkUrlSuggestionQueueRequest.getEmail());
            bookMarkUrlSuggestionQueue.setSuggested_date(new Date());
            bookMarkUrlSuggestionQueue.setSuggestion_text(bookMarkUrlSuggestionQueueRequest.getSuggestion_text());
            return bookMarkUrlSuggestionQueueRepository.save(bookMarkUrlSuggestionQueue);
        }
        else {
            throw new NotFoundException("Requested card is not available for suggestion.");
        }
    }

    /**
     * Gets all suggestion for card.
     *
     * @param email the email
     * @return the all suggestion for card
     */
    public List<BookMarkUrlSuggestionQueue> getAllSuggestionForCard(String email) {
        List<BookMarkUrlCardResponse> bookMarkUrlCardResponseList = getAllCard(email);
        List <BookMarkUrlSuggestionQueue> result = new ArrayList<>();
        bookMarkUrlCardResponseList.stream().forEach((bookMarkUrlCardResponse -> result.addAll(bookMarkUrlSuggestionQueueRepository.getAllSuggestionQueueByCardId(bookMarkUrlCardResponse.getId()))));
       return result;
    }

    /**
     * Delete suggestion for card.
     *
     * @param suggestionId the suggestion id
     */
    public void deleteSuggestionForCard(int suggestionId) {
        bookMarkUrlSuggestionQueueRepository.deleteById(suggestionId);
    }
}
