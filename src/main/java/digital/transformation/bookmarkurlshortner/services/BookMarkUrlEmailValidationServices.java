package digital.transformation.bookmarkurlshortner.services;

import digital.transformation.bookmarkurlshortner.api.BookMarkUrlEmailValidationApi;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlEmailValidationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("EmailValidationServices")
public class BookMarkUrlEmailValidationServices implements BookMarkUrlEmailValidationApi {

    @Autowired
    private BookMarkUrlEmailValidationManager bookMarkUrlEmailValidationManager;

    @Override
    public ResponseEntity<Map> emailValidation(String emailId) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlEmailValidationManager.getEmailValidationManager(emailId), HttpStatus.OK);
    }
}
