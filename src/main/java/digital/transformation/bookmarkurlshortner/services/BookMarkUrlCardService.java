package digital.transformation.bookmarkurlshortner.services;

import digital.transformation.bookmarkurlshortner.api.BookMarkUrlCardApi;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlCardManager;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlIconResponse;
import digital.transformation.bookmarkurlshortner.util.DigitalUtil;
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

@RestController("CardService")
public class BookMarkUrlCardService implements BookMarkUrlCardApi {

    @Autowired
    private BookMarkUrlCardManager bookMarkUrlCardManager;

    @Override
    public ResponseEntity<BookMarkUrlCardResponse> createCard(BookMarkUrlCardRequest bookMarkUrlCardRequest) throws BookMarkUrlDigitalOrgException, IOException {
        if(!DigitalUtil.isValid(bookMarkUrlCardRequest.getCreated_by()) || !DigitalUtil.isValid(bookMarkUrlCardRequest.getUpdated_by())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookMarkUrlCardManager.createCard(bookMarkUrlCardRequest), HttpStatus.CREATED);
    }

    public ResponseEntity uplaodImage(int cardId, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        BookMarkUrlIcon bookMarkUrlIcon = new BookMarkUrlIcon(file.getOriginalFilename(), file.getContentType(), DigitalUtil.compressBytes(file.getBytes()),cardId);
        bookMarkUrlCardManager.uplaodImage(bookMarkUrlIcon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookMarkUrlIconResponse> downloadImage(int cardId) throws IOException {
        BookMarkUrlIcon bookMarkUrlIcon = bookMarkUrlCardManager.downloadImage(cardId);
        BookMarkUrlIconResponse bookMarkUrlIconResponse = new BookMarkUrlIconResponse(bookMarkUrlIcon.getName(), bookMarkUrlIcon.getType(), DigitalUtil.decompressBytes(bookMarkUrlIcon.getFile()), bookMarkUrlIcon.getCardId(), bookMarkUrlIcon.getId());
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
        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }

    @Override
    public ResponseEntity deleteCard(int cardId, String email) throws BookMarkUrlDigitalOrgException {
        BookMarkUrlCard bookMarkUrlCard = bookMarkUrlCardManager.getCardById(cardId);
        if(bookMarkUrlCard.getCreated_by().equals(email)) {
            bookMarkUrlCardManager.deleteCard(cardId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
