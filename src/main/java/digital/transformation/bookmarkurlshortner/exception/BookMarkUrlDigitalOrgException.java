package digital.transformation.bookmarkurlshortner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BookMarkUrlDigitalOrgException extends RuntimeException  {

    public BookMarkUrlDigitalOrgException() {

    }

    public BookMarkUrlDigitalOrgException(String message) {
        super(message);
    }

    public BookMarkUrlDigitalOrgException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookMarkUrlDigitalOrgException(Throwable cause) {
        super(cause);
    }

    public BookMarkUrlDigitalOrgException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }
}
