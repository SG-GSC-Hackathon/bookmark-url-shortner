package digital.transformation.bookmarkurlshortner.services;

import co.elastic.apm.api.CaptureSpan;
import digital.transformation.bookmarkurlshortner.api.BookMarkUrlGroupApi;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlGroupManager;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardInGroupRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type BookMarkUrlGroup service.
 */
@RestController("GroupService")
public class BookMarkUrlGroupService implements BookMarkUrlGroupApi {

    @Autowired
    private BookMarkUrlGroupManager bookMarkUrlGroupManager;

    @Override
    public ResponseEntity createGroup(BookMarkUrlGroupRequest bookMarkUrlGroupRequest) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.createGroup(bookMarkUrlGroupRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateGroup(BookMarkUrlGroupUpdateRequest groupRequest) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.updateGroup(groupRequest), HttpStatus.OK);
    }


    @Override
    public BookMarkUrlGroup getGroupbyId(Integer cardId) throws BookMarkUrlDigitalOrgException {
        return bookMarkUrlGroupManager.getGroupById(cardId);
    }


    @Override
    public ResponseEntity addUserToGroup(String userEmail, String adminEmail, int groupId) throws BookMarkUrlDigitalOrgException {
        bookMarkUrlGroupManager.addUserToGroup(userEmail, adminEmail, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeUserFromGroup(String userEmail,String email, int groupId) throws BookMarkUrlDigitalOrgException {
        bookMarkUrlGroupManager.removeUserToGroup(userEmail,email, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @CaptureSpan(value = "getAllcard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllGroupService(String email) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.getAllGroupManager(email), HttpStatus.OK);
    }

    @Override
    @CaptureSpan(value = "getAllcard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllGroupServiceforOwner(String email) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.getAllGroupManagerForOwner(email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity addCardToGroup(BookMarkUrlCardInGroupRequest bookMarkUrlCardInGroupRequest) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.addCardToGroupManager(bookMarkUrlCardInGroupRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeCardToGroup(BookMarkUrlCardInGroupRequest bookMarkUrlCardInGroupRequest) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.removeCardToGroupManager(bookMarkUrlCardInGroupRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getAllCardForGroup(int groupId) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.getAllCardForGroupManager(groupId), HttpStatus.OK);
    }
}
