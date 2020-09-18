package digital.transformation.bookmarkurlshortner.exception;

import digital.transformation.bookmarkurlshortner.model.mapper.BookMarkUrlErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Digital exception handler.
 */
@Slf4j
@RestControllerAdvice @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BookMarkUrlDigitalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
            body = new BookMarkUrlErrorMessage("An Internal error occurred").toJson();
            log.error("An internal error occurred");
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    /**
     * Handle bad request exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(BookMarkUrlBadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BookMarkUrlBadRequestException ex, WebRequest request) {
        return new ResponseEntity(new BookMarkUrlErrorMessage(ex.getMessage()).toJson(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle not found exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(BookMarkUrlNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(BookMarkUrlNotFoundException ex, WebRequest request) {
        return new ResponseEntity(new BookMarkUrlErrorMessage(ex.getMessage()).toJson(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle forbidden exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(BookMarkUrlForbiddenException.class)
    protected ResponseEntity<Object> handleForbiddenException(BookMarkUrlForbiddenException ex, WebRequest request) {
        return new ResponseEntity(new BookMarkUrlErrorMessage(ex.getMessage()).toJson(), HttpStatus.FORBIDDEN);
    }

}
