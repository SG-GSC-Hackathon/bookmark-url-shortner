package digital.transformation.bookmarkurlshortner.api;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/email-validate")
@Api(tags = "Email Validation Services", description = "BookMarkUrlCard Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface BookMarkUrlEmailValidationApi {

    @ApiOperation(value = "email validation ", nickname = "validateEmail", notes = "BookMarkUrlUser to get a card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "email type unknown")})
    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<Map> emailValidation(@ApiParam(value = "email", required = true) @RequestBody String email) throws BookMarkUrlDigitalOrgException;
}
