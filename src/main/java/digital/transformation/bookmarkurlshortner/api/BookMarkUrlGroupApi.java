package digital.transformation.bookmarkurlshortner.api;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/group")
@Api(tags = "BookMarkUrlGroup Services", description = "BookMarkUrlGroup Api")
@CrossOrigin(origins = {"https://localhost:8081", "http://localhost:4200"})
public interface BookMarkUrlGroupApi {

    @ApiOperation(value = "Create new group", notes = "BookMarkUrlUser to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    ResponseEntity createGroup(@ApiParam(value = "group object", required = true) @RequestBody BookMarkUrlGroupRequest group) throws BookMarkUrlDigitalOrgException;

    @ApiOperation(value = "Update existing group", nickname = "updateGroup", notes = "BookMarkUrlUser to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping(path = "/{group-id}", consumes = "application/json", produces = "application/json")
    ResponseEntity updateGroup(@ApiParam(value = "group object", required = true) @RequestBody BookMarkUrlGroupRequest group, @PathVariable("group-id") int groupId) throws BookMarkUrlDigitalOrgException;

    @ApiOperation(value = "delete group", nickname = "deleteGroup", notes = "delete BookMarkUrlGroup", response = BookMarkUrlGroup.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = BookMarkUrlGroup.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @GetMapping(path = "/{group-id}", produces = "application/json")
    public BookMarkUrlGroup getGroupbyId(@ApiParam(value = "group object", required = true) @PathVariable("card-id") Integer cardId) throws BookMarkUrlDigitalOrgException;

    @ApiOperation(value = "delete group", nickname = "deleteGroup", notes = "delete BookMarkUrlGroup", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "accepted operation", response = Void.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @DeleteMapping(path = "/{group-id}", produces = "application/json")
    public void deleteGroup(@ApiParam(value = "group object", required = true) @PathVariable("group-id") Integer groupId) throws BookMarkUrlDigitalOrgException;


    @ApiOperation(value = "add user to group", nickname = "createGroup", notes = "BookMarkUrlUser to create New group", response = BookMarkUrlGroup.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = BookMarkUrlGroup.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @PostMapping(path = "/add-user-to-group", consumes = "application/json", produces = "application/json")
    ResponseEntity addUserToGroup(@ApiParam(value = "group object", required = true) @RequestBody String user, String admin, int groupId) throws BookMarkUrlDigitalOrgException;

    @ApiOperation(value = "remove user from group", nickname = "createGroup", notes = "BookMarkUrlUser to create New group", response = BookMarkUrlGroup.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = BookMarkUrlGroup.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @PostMapping(path = "/remove-user-from-group", consumes = "application/json", produces = "application/json")
    ResponseEntity removeUserFromGroup(@ApiParam(value = "group object", required = true) @RequestBody String user, String admin,int groupId) throws BookMarkUrlDigitalOrgException;
}
