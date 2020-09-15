package digital.transformation.bookmarkurlshortner.api;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlIconResponse;
import io.swagger.annotations.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/card")
@Api(tags = "BookMarkUrlCard Services", description = "BookMarkUrlCard Api")
@CrossOrigin(origins = {"https://bookmark-url-shortner.herokuapp.com/", "http://localhost:4200"})
public interface BookMarkUrlCardApi {

    @ApiOperation(value = "Create new card", notes = "BookMarkUrlUser to create new card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class),
                           @ApiResponse(code = 400, message = "Invalid Request")})
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity createCard(@RequestBody BookMarkUrlCardRequest bookMarkUrlCardRequest) throws BookMarkUrlDigitalOrgException, IOException;

    @ApiOperation(value = "Upload Image", notes = "Upload custom image", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
                           @ApiResponse(code = 400, message = "Unknown image type")})
    @PostMapping(path = "/upload/{card-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<BookMarkUrlCardResponse> uplaodImage(@PathVariable("card-id") int cardId , @RequestPart("file") MultipartFile file) throws IOException ;

    @ApiOperation(value = "Download image", notes = "Download image for card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(path = "/download/{card-id}")
    public ResponseEntity<BookMarkUrlIconResponse> downloadImage(@PathVariable("card-id") int cardId) throws IOException ;

    @ApiOperation(value = "Download image", notes = "Download image for card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(path = "/download-octa/{card-id}")
    public ResponseEntity<Resource> downloadImageocta(@PathVariable("card-id") int cardId) throws IOException ;


    @ApiOperation(value = "Delete card", notes = "Delete requested card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Deleted", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping(path = "/{card-id}/{email}", produces = "application/json")
    ResponseEntity deleteCard(@PathVariable("card-id") int cardId, @PathVariable("email") String email) throws BookMarkUrlDigitalOrgException;

    @ApiOperation(value = "Get All cards ", notes = "Get all card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @PostMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List> getAllcard(@RequestBody String email) throws BookMarkUrlDigitalOrgException;
}
