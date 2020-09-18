package digital.transformation.bookmarkurlshortner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Digital org exception.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BookMarkUrlDigitalOrgException extends RuntimeException {

    /**
     * Instantiates a new Digital org exception.
     */
    public BookMarkUrlDigitalOrgException() {

    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param message the message
     */
    public BookMarkUrlDigitalOrgException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BookMarkUrlDigitalOrgException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param cause the cause
     */
    public BookMarkUrlDigitalOrgException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSupression   the enable supression
     * @param writableStackTrace the writable stack trace
     */
    public BookMarkUrlDigitalOrgException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }
}
