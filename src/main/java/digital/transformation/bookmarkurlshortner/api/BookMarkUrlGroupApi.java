package digital.transformation.bookmarkurlshortner.api;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.request.SharedUserToGroupRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardInGroupRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupUpdateRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The interface BookMarkUrlGroup api.
 */
@RequestMapping("/group")
@Api(tags = "BookMarkUrlGroup Services", description = "BookMarkUrlGroup Api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface BookMarkUrlGroupApi {

    /**
     * Create group response entity.
     *
     * @param group the group
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Create new group", notes = "BookMarkUrlUser to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity createGroup(@ApiParam(value = "group object", required = true) @RequestBody BookMarkUrlGroupRequest group) throws BookMarkUrlDigitalOrgException;

    /**
     * Update group response entity.
     *
     * @param group the group
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Update existing group", notes = "BookMarkUrlUser to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PatchMapping(path = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity updateGroup(@RequestBody BookMarkUrlGroupUpdateRequest group) throws BookMarkUrlDigitalOrgException;

    /**
     * Gets groupby id.
     *
     * @param cardId the card id
     * @return the groupby id
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "delete group", notes = "delete BookMarkUrlGroup", response = BookMarkUrlGroup.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = BookMarkUrlGroup.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @GetMapping(path = "/{group-id}", produces = "application/json")
    BookMarkUrlGroup getGroupbyId(@ApiParam(value = "group object", required = true) @PathVariable("card-id") Integer cardId) throws BookMarkUrlDigitalOrgException;

    /**
     * Add user to group response entity.
     *
     * @param userEmail  the user email
     * @param adminEmail the admin email
     * @param groupId    the group id
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "add user to group", notes = "add user to group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @PostMapping(path = "/user-to-group", consumes = "application/json", produces = "application/json")
    ResponseEntity addUserToGroup(@RequestBody SharedUserToGroupRequest sharedUserToGroupRequest) throws BookMarkUrlDigitalOrgException;

    /**
     * Remove user from group response entity.
     *
     * @param email   the email
     * @param groupId the group id
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "remove user from group", notes = "remove BookMarkUrlUser from group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No content", response = ResponseEntity.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @PostMapping(path = "/user-from-group", consumes = "application/json", produces = "application/json")
    ResponseEntity removeUserFromGroup(@RequestBody SharedUserToGroupRequest sharedUserToGroupRequest) throws BookMarkUrlDigitalOrgException;

    /**
     * Gets all group service.
     *
     * @param email the email
     * @return the all group service
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All group ", notes = "Get all group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @GetMapping(path = "/all/{email}", produces = "application/json")
    ResponseEntity<List> getAllGroupService(@ApiParam(value = "email", required = true) @PathVariable("email") String email) throws BookMarkUrlDigitalOrgException;

    /**
     * Gets all group servicefor owner.
     *
     * @param email the email
     * @return the all group servicefor owner
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All group for owner ", notes = "Get all group for owner", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @GetMapping(path = "/all/admin/{email}", produces = "application/json")
    ResponseEntity<List> getAllGroupServiceforOwner(@ApiParam(value = "email", required = true) @PathVariable("email") String email) throws BookMarkUrlDigitalOrgException;

    /**
     * Add card to group response entity.
     *
     * @param bookMarkUrlCardInGroupRequest the card in group request
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Add card to group", notes = "Admin of group can be add card.", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @PostMapping(path = "/add-card-to-group", consumes = "application/json", produces = "application/json")
    ResponseEntity addCardToGroup(@ApiParam(value = "email", required = true) @RequestBody BookMarkUrlCardInGroupRequest bookMarkUrlCardInGroupRequest) throws BookMarkUrlDigitalOrgException;

    /**
     * Remove card to group response entity.
     *
     * @param bookMarkUrlCardInGroupRequest the card in group request
     * @return the response entity
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Remove user to group", notes = "Admin of group can be remove card.", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @PostMapping(path = "/remove-card-from-group", consumes = "application/json", produces = "application/json")
    ResponseEntity removeCardToGroup(@ApiParam(value = "email", required = true) @RequestBody BookMarkUrlCardInGroupRequest bookMarkUrlCardInGroupRequest) throws BookMarkUrlDigitalOrgException;

    /**
     * Gets all card for group.
     *
     * @param groupId the group id
     * @return the all card for group
     * @throws BookMarkUrlDigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All card from group", notes = "Get All card from group.", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "BookMarkUrlGroup type unknown")})
    @GetMapping(path = "/card-from-group/{group-id}", consumes = "*/*", produces = "application/json")
    ResponseEntity<List> getAllCardForGroup(@PathVariable("group-id") int groupId) throws BookMarkUrlDigitalOrgException;

}
