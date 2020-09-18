package digital.transformation.bookmarkurlshortner.api;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlSuggestionQueue;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardUpdateRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlSuggestionQueueRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlIconResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The interface BookMarkUrlCard api.
 */
@RequestMapping("/card")
@Api(tags = "BookMarkUrlCard Services")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface BookMarkUrlCardApi {

    /**
     * Create card response entity.
     *
     * @param bookMarkUrlCardRequest the card request
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     * @throws IOException         the io exception
     */
    @ApiOperation(value = "Create new card", notes = "BookMarkUrlUser to create new card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Invalid Request")})
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity<BookMarkUrlCardResponse> createCard(@RequestBody BookMarkUrlCardRequest bookMarkUrlCardRequest) throws BookMarkUrlDigitalOrgException, IOException;

    /**
     * Update card response entity.
     *
     * @param cardRequest the card request
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     * @throws IOException         the io exception
     */
    @ApiOperation(value = "Update existing card", notes = "BookMarkUrlUser to update existing card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Invalid Request")})
    @PatchMapping(path = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity<BookMarkUrlCardResponse> updateCard(@RequestBody BookMarkUrlCardUpdateRequest cardRequest) throws BookMarkUrlDigitalOrgException, IOException;

    /**
     * Uplaod image response entity.
     *
     * @param cardId the card id
     * @param file   the file
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation(value = "Upload Image", notes = "Upload custom image", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Unknown image type")})
    @PostMapping(path = "/upload/{card-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    ResponseEntity<BookMarkUrlCardResponse> uplaodImage(@PathVariable("card-id") int cardId, @RequestPart("file") MultipartFile file) throws IOException;

    /**
     * Download image response entity.
     *
     * @param cardId the card id
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation(value = "Download image", notes = "Download image for card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(path = "/download/{card-id}")
    ResponseEntity<BookMarkUrlIconResponse> downloadImage(@PathVariable("card-id") int cardId) throws IOException;

    /**
     * Download imageocta response entity.
     *
     * @param cardId the card id
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation(value = "Download image", notes = "Download image for card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(path = "/download-octa/{card-id}")
    ResponseEntity<Resource> downloadImageocta(@PathVariable("card-id") int cardId) throws IOException;


    /**
     * Delete card response entity.
     *
     * @param cardId the card id
     * @param email  the email
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Delete card", notes = "Delete requested card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Deleted", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping(path = "/{card-id}/{email}", produces = "application/json")
    ResponseEntity deleteCard(@PathVariable("card-id") int cardId, @PathVariable("email") String email) throws BookMarkUrlDigitalOrgException;

    /**
     * Gets allcard.
     *
     * @param email the email
     * @return the allcard
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All bookMarkUrlCards ", notes = "Get all card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @PostMapping(path = "/all", produces = "application/json")
    ResponseEntity<List> getAllcard(@RequestBody String email) throws BookMarkUrlDigitalOrgException;


    /**
     * Gets allcard for owner.
     *
     * @param email the email
     * @return the allcard for owner
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All bookMarkUrlCards Owner", notes = "Get all card for Owner", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @PostMapping(path = "/all/admin", produces = "application/json")
    ResponseEntity<List> getAllcardForOwner(@RequestBody String email) throws BookMarkUrlDigitalOrgException;

    /**
     * Suggestion for card response entity.
     *
     * @param bookMarkUrlSuggestionQueueRequest the suggestion queue request
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     * @throws NotFoundException   the not found exception
     */
    @ApiOperation(value = "Suggestion For BookMarkUrlCard", notes = "Suggestion For BookMarkUrlCard", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @PostMapping(path = "/suggestion", produces = "application/json")
    ResponseEntity<BookMarkUrlSuggestionQueue> suggestionForCard(@RequestBody BookMarkUrlSuggestionQueueRequest bookMarkUrlSuggestionQueueRequest) throws BookMarkUrlDigitalOrgException, NotFoundException;


    /**
     * Suggestion for card response entity.
     *
     * @param suggestionId the suggestion id
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     * @throws NotFoundException   the not found exception
     */
    @ApiOperation(value = "Delete Suggestion For BookMarkUrlCard", notes = " Delete Suggestion For BookMarkUrlCard", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @DeleteMapping(path = "/suggestion/{suggestion-id}", produces = "application/json")
    ResponseEntity<BookMarkUrlSuggestionQueue> suggestionForCard(@PathVariable("suggestion-id") int suggestionId) throws BookMarkUrlDigitalOrgException, NotFoundException;

    /**
     * Gets all suggestion for card.
     *
     * @param email the email
     * @return the all suggestion for card
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     * @throws NotFoundException   the not found exception
     */
    @ApiOperation(value = "Suggestion For BookMarkUrlCard", notes = "Suggestion For BookMarkUrlCard", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @GetMapping(path = "/suggestion/{email}", produces = "application/json")
    ResponseEntity<List<BookMarkUrlSuggestionQueue>> getAllSuggestionForCard(@PathVariable("email") String email) throws BookMarkUrlDigitalOrgException, NotFoundException;
}
