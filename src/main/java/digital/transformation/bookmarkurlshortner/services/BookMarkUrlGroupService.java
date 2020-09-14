package digital.transformation.bookmarkurlshortner.services;

import digital.transformation.bookmarkurlshortner.api.BookMarkUrlGroupApi;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.managers.BookMarkUrlGroupManager;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController("GroupService")
public class BookMarkUrlGroupService implements BookMarkUrlGroupApi {

    @Autowired
    private BookMarkUrlGroupManager bookMarkUrlGroupManager;

    @Override
    public ResponseEntity createGroup(BookMarkUrlGroupRequest bookMarkUrlGroupRequest) throws BookMarkUrlDigitalOrgException {
        return new ResponseEntity<>(bookMarkUrlGroupManager.createGroup(bookMarkUrlGroupRequest), HttpStatus.CREATED);
    }

    @Override
    public BookMarkUrlGroup getGroupbyId(Integer cardId) throws BookMarkUrlDigitalOrgException {
        return bookMarkUrlGroupManager.getGroupById(cardId);
    }

    @Override
    public ResponseEntity updateGroup(BookMarkUrlGroupRequest bookMarkUrlGroupRequest, int groupId) throws BookMarkUrlDigitalOrgException {
        BookMarkUrlGroup bookMarkUrlGroup = bookMarkUrlGroupManager.getGroupById(groupId);
        if(bookMarkUrlGroup == null) {
            throw new BookMarkUrlDigitalOrgException("Requested BookMarkUrlGroup is not found.");
        }
        //TODO : only admin can update the bookMarkUrlGroup
        return new ResponseEntity<>(bookMarkUrlGroupManager.createGroup(bookMarkUrlGroupRequest), HttpStatus.OK);
    }

    @Override
    public void deleteGroup(Integer groupId) throws BookMarkUrlDigitalOrgException {
//        BookMarkUrlGroup group = groupManager.getGroupById(groupId);
        //TODO : Valid group validation
        //TODO : only admin can update the group
        //TODO : By deleting group will de-associate all the cards from that group
        bookMarkUrlGroupManager.deleteGroup(groupId);
    }

    @Override
    public ResponseEntity addUserToGroup(String user, String admin, int groupId) throws BookMarkUrlDigitalOrgException {
//        TODO : validation : admin is valid admin for given group
        bookMarkUrlGroupManager.addUserToGroup(user, bookMarkUrlGroupManager.getGroupById(groupId).getName(), admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeUserFromGroup(String user, String admin, int groupId) throws BookMarkUrlDigitalOrgException {
        //        TODO : validation : admin is valid admin for given group
        bookMarkUrlGroupManager.removeUserToGroup(user, bookMarkUrlGroupManager.getGroupById(groupId).getName(), admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
