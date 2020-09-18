package digital.transformation.bookmarkurlshortner.services;

import co.elastic.apm.api.CaptureSpan;
import digital.transformation.bookmarkurlshortner.api.BookMarkUrlCardApi;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlBadRequestException;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlCardManager;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlSuggestionQueue;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardUpdateRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlSuggestionQueueRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlIconResponse;
import digital.transformation.bookmarkurlshortner.util.BookMarkUrlDigitalUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The type BookMarkUrlCard service.
 */
@RestController("CardService")
public class BookMarkUrlCardService implements BookMarkUrlCardApi {

    @Autowired
    private BookMarkUrlCardManager bookMarkUrlCardManager;

    @Override
    public ResponseEntity<BookMarkUrlCardResponse> createCard(BookMarkUrlCardRequest bookMarkUrlCardRequest) throws BookMarkUrlDigitalOrgException, IOException {
        try {
            return new ResponseEntity<>(bookMarkUrlCardManager.createCard(bookMarkUrlCardRequest), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new BookMarkUrlBadRequestException(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<BookMarkUrlCardResponse> updateCard(BookMarkUrlCardUpdateRequest cardRequest) throws BookMarkUrlDigitalOrgException, IOException {
        return new ResponseEntity<>(bookMarkUrlCardManager.updateCard(cardRequest), HttpStatus.OK);
    }

    public ResponseEntity<BookMarkUrlCardResponse> uplaodImage(int cardId, @RequestParam("file") MultipartFile file) throws IOException {
        BookMarkUrlIcon bookMarkUrlIcon = new BookMarkUrlIcon(file.getOriginalFilename(), file.getContentType(), cardId, BookMarkUrlDigitalUtil.compressBytes(file.getBytes()));
        bookMarkUrlCardManager.uplaodImage(bookMarkUrlIcon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookMarkUrlIconResponse> downloadImage(int cardId) throws IOException {
        BookMarkUrlIcon bookMarkUrlIcon = bookMarkUrlCardManager.downloadImage(cardId);
        BookMarkUrlIconResponse bookMarkUrlIconResponse = new BookMarkUrlIconResponse(bookMarkUrlIcon.getName(), bookMarkUrlIcon.getType(), BookMarkUrlDigitalUtil.decompressBytes(bookMarkUrlIcon.getFile()), bookMarkUrlIcon.getCard_id(), bookMarkUrlIcon.getId());
        return new ResponseEntity<>(bookMarkUrlIconResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Resource> downloadImageocta(int cardId) throws IOException {
        BookMarkUrlIcon bookMarkUrlIcon = bookMarkUrlCardManager.downloadImage(cardId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(bookMarkUrlIcon.getFile());
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("image/png")).body(resource);
    }

    @Override
    public ResponseEntity deleteCard(int cardId, String email) throws BookMarkUrlDigitalOrgException {
        bookMarkUrlCardManager.deleteCard(bookMarkUrlCardManager.getCardById(cardId), email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @CaptureSpan(value = "getAllCard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllcard(String email) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlCardManager.getAllCard(email), HttpStatus.OK);
    }

    @Override
    @CaptureSpan(value = "getAllCard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllcardForOwner(String email) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlCardManager.getAllcardForOwner(email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookMarkUrlSuggestionQueue> suggestionForCard(BookMarkUrlSuggestionQueueRequest bookMarkUrlSuggestionQueueRequest) throws BookMarkUrlDigitalOrgException, NotFoundException {
        return new ResponseEntity<>(bookMarkUrlCardManager.suggestionForCard(bookMarkUrlSuggestionQueueRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity suggestionForCard(int suggestionId) throws BookMarkUrlDigitalOrgException, NotFoundException {
        bookMarkUrlCardManager.deleteSuggestionForCard(suggestionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<BookMarkUrlSuggestionQueue>> getAllSuggestionForCard(String email) throws BookMarkUrlDigitalOrgException, NotFoundException {
        return new ResponseEntity<>(bookMarkUrlCardManager.getAllSuggestionForCard(email), HttpStatus.OK);
    }

}
