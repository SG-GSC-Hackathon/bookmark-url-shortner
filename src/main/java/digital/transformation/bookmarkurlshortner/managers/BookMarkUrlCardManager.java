package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlCardRepository;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlIconRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMarkUrlCardManager {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int base = allowedCharacters.length;

    @Autowired
    private BookMarkUrlCardRepository bookMarkUrlCardRepository;

    @Autowired
    BookMarkUrlIconRepository bookMarkUrlIconRepository;

    public BookMarkUrlCardResponse createCard(BookMarkUrlCardRequest bookMarkUrlCardRequest)  throws IOException {
        ModelMapper reqModelMapper = new ModelMapper();
        BookMarkUrlCard bookMarkUrlCard = reqModelMapper.map(bookMarkUrlCardRequest, BookMarkUrlCard.class);
        bookMarkUrlCard.setCreated_date(new Date());
        bookMarkUrlCard.setUpdated_date(new Date());

        if(bookMarkUrlCardRequest.getGroup_name() != null) {
//        TODO: build short url without expire if group,

        }
        else {
//        TODO: build short url with expire,
            StringBuilder encodedString = new StringBuilder();
            int input= 1;
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }
/*
            if(bookMarkUrlCard.getFile() == null) {
                ClassPathResource classPathResource = new ClassPathResource("files/favicon.png");
                byte[] bytes = new byte[(int) classPathResource.contentLength()];
                classPathResource.getInputStream().read(bytes);
                bookMarkUrlCard.setFile(compressBytes(bytes));

                bookMarkUrlCard.setFile(compressBytes(bookMarkUrlCardRequest.getFile().getBytes()));

            }
*/

        BookMarkUrlCard response = bookMarkUrlCardRepository.save(bookMarkUrlCard);

        ModelMapper resModelMapper = new ModelMapper();
        BookMarkUrlCardResponse bookMarkUrlCardResponse = resModelMapper.map(response, BookMarkUrlCardResponse.class);
//        MultipartFile file = new DigitalMultipartFile(decompressBytes(response.getFile()));
//        bookMarkUrlCardResponse.setFile(file);

        return bookMarkUrlCardResponse;
    }

/*
    public String convertToShortUrl(UrlLongRequest request) {
        var url = new URL();
        url.setLongUrl(request.getLongUrl());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        var entity = urlRepository.save(url);

        return conversion.encode(entity.getId());
    }
    */

    public BookMarkUrlCard getCardById(Integer cardId) {
        Optional<BookMarkUrlCard> card = bookMarkUrlCardRepository.findById(cardId);
        return card.get();
    }

    public List<BookMarkUrlCardResponse> getAllCard() {
        List<BookMarkUrlCardResponse> cardList = bookMarkUrlCardRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, BookMarkUrlCardResponse.class), Collectors.toList()));
        return cardList;
    }

    public void uplaodImage(BookMarkUrlIcon bookMarkUrlIcon) {
        bookMarkUrlIconRepository.save(bookMarkUrlIcon);
    }

    public BookMarkUrlIcon downloadImage(int cardId) {
        Optional<BookMarkUrlIcon> icon = bookMarkUrlIconRepository.findById(cardId);
        return icon.get();
    }

    public void deleteCard(int cardId) {
        bookMarkUrlCardRepository.deleteById(cardId);
    }
}
