package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlDigitalOrgException;
import digital.transformation.bookmarkurlshortner.exception.BookMarkUrlForbiddenException;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCardInGroup;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlCardInGroupRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupUpdateRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlGroupResponse;
import digital.transformation.bookmarkurlshortner.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * The type BookMarkUrlGroup manager.
 */
@Component
public class BookMarkUrlGroupManager {

    @Autowired
    private BookMarkUrlGroupRepository bookMarkUrlGroupRepository;

    @Autowired
    private BookMarkUrlCardRepository bookMarkUrlCardRepository;

    @Autowired
    private BookMarkUrlUserInGroupRepository userInGroupRepository;

    @Autowired
    private BookMarkUrlCardInGroupRepository bookMarkUrlCardInGroupRepository;

    @Autowired
    private BookMarkUrlUrlRepository bookMarkUrlUrlRepository;

    /**
     * Gets group.
     *
     * @param cardId the card id
     * @return the group
     */
    public BookMarkUrlGroup getGroup(Integer cardId) {
        Optional<BookMarkUrlGroup> card = bookMarkUrlGroupRepository.findById(cardId);
        return card.get();
    }

    /**
     * Create group group response.
     *
     * @param bookMarkUrlGroupRequest the group request
     * @return the group response
     */
    public BookMarkUrlGroupResponse createGroup(BookMarkUrlGroupRequest bookMarkUrlGroupRequest) {

        ModelMapper modelMapper = new ModelMapper();
        BookMarkUrlGroup bookMarkUrlGroupMapper = modelMapper.map(bookMarkUrlGroupRequest, BookMarkUrlGroup.class);
        bookMarkUrlGroupMapper.setCreated_date(new Date());
        bookMarkUrlGroupMapper.setUpdated_date(new Date());
        bookMarkUrlGroupMapper.setActive(true);

        BookMarkUrlGroup bookMarkUrlGroup = bookMarkUrlGroupRepository.save(bookMarkUrlGroupMapper);

        BookMarkUrlUserInGroup bookMarkUrlUserInGroup = new BookMarkUrlUserInGroup();
        bookMarkUrlUserInGroup.setGroup_id(bookMarkUrlGroup.getId());
        bookMarkUrlUserInGroup.setAdded_by(bookMarkUrlGroup.getCreated_by());
        bookMarkUrlUserInGroup.setAdded_date(new Date());
        bookMarkUrlUserInGroup.setEmail(bookMarkUrlGroup.getCreated_by());
        userInGroupRepository.save(bookMarkUrlUserInGroup);

        ModelMapper resModelMapper = new ModelMapper();
        BookMarkUrlGroupResponse bookMarkUrlGroupResponse = resModelMapper.map(bookMarkUrlGroup, BookMarkUrlGroupResponse.class);
        bookMarkUrlGroupResponse.setHasAdmin(true);
        return bookMarkUrlGroupResponse;
    }

    /**
     * Update group atomic reference.
     *
     * @param groupRequest the group request
     * @return the atomic reference
     */
    public AtomicReference<BookMarkUrlGroupResponse> updateGroup(BookMarkUrlGroupUpdateRequest groupRequest) {
        AtomicReference<BookMarkUrlGroupResponse> groupResponse = null;
        BookMarkUrlGroup bookMarkUrlGroup = getGroupById(groupRequest.getId());
        if (bookMarkUrlGroup == null) {
            throw new BookMarkUrlDigitalOrgException("Requested BookMarkUrlGroup is not found.");
        }

        userInGroupRepository.findAllUserInGroupByGroupID(bookMarkUrlGroup.getId()).stream().map(bookMarkUrlUserInGroup -> {
            if (bookMarkUrlUserInGroup.getEmail().equals(groupRequest.getUpdated_by())) {
                userInGroupRepository.deleteById(bookMarkUrlUserInGroup.getId());
            }
            ModelMapper modelMapper = new ModelMapper();
            BookMarkUrlGroup bookMarkUrlGroupMapper = modelMapper.map(groupRequest, BookMarkUrlGroup.class);
            bookMarkUrlGroupMapper.setUpdated_date(new Date());
            bookMarkUrlGroupMapper.setActive(true);

            ModelMapper resModelMapper = new ModelMapper();
            groupResponse.set(resModelMapper.map(bookMarkUrlGroupRepository.save(bookMarkUrlGroupMapper), BookMarkUrlGroupResponse.class));
            groupResponse.get().setHasAdmin(true);

            return groupResponse;
        });
        return groupResponse;
    }

    /**
     * Gets group by id.
     *
     * @param cardId the card id
     * @return the group by id
     */
    public BookMarkUrlGroup getGroupById(Integer cardId) {
        Optional<BookMarkUrlGroup> group = bookMarkUrlGroupRepository.findById(cardId);
        return group.get();
    }


    /**
     * Add user to group.
     *
     * @param userEmail  the user email
     * @param adminEmail the admin email
     * @param groupId    the group id
     */
    public void addUserToGroup(String userEmail, String adminEmail, int groupId) {
        List<BookMarkUrlUserInGroup> bookMarkUrlUserInGroups = userInGroupRepository.findAllUserInGroupByGroupID(groupId);
        bookMarkUrlUserInGroups.stream().map(bookMarkUrlUserInGroup -> {
            if (bookMarkUrlUserInGroup.getAdded_by().equals(adminEmail)) {
                BookMarkUrlUserInGroup user = new BookMarkUrlUserInGroup();
                user.setEmail(userEmail);
                user.setGroup_id(bookMarkUrlUserInGroup.getGroup_id());
                user.setAdded_date(new Date());
                user.setAdded_by(adminEmail);
                userInGroupRepository.save(user);
            }
            return "";
        }).collect(Collectors.toList());
    }

    /**
     * Remove user to group.
     *
     * @param email   the email
     * @param groupId the group id
     */
    public void removeUserToGroup(String userEmail, String adminEmail, int groupId) {
        List<BookMarkUrlUserInGroup> bookMarkUrlUserInGroups = userInGroupRepository.findAllUserInGroupByGroupID(groupId);
        if (bookMarkUrlUserInGroups.size() > 1) {
            bookMarkUrlUserInGroups.stream().map(bookMarkUrlUserInGroup -> {
                if (bookMarkUrlUserInGroup.getAdded_by().equals(adminEmail)) {
                    userInGroupRepository.deleteUserfromGroup(userEmail);
                }

                return "";
            });
        } else {
            throw new BookMarkUrlDigitalOrgException("You are only admin of this group, please other as a admin to this group, then remove");
        }
    }

    /**
     * Gets all group manager.
     *
     * @param emailId the email id
     * @return the all group manager
     */
    public List<BookMarkUrlGroupResponse> getAllGroupManager(String emailId) {
        List<BookMarkUrlGroupResponse> groupList = bookMarkUrlGroupRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, BookMarkUrlGroupResponse.class), Collectors.toList()));

        List<BookMarkUrlGroupResponse> bookMarkUrlGroupResponseList = groupList.stream().map(bookMarkUrlGroupResponse -> {
            if (bookMarkUrlGroupResponse.getCreated_by().equals(emailId)) {
                bookMarkUrlGroupResponse.setHasAdmin(true);
            } else {
                bookMarkUrlGroupResponse.setHasAdmin(false);
            }
            return bookMarkUrlGroupResponse;
        }).collect(Collectors.toList());
        return bookMarkUrlGroupResponseList;
    }

    /**
     * Gets all group manager for owner.
     *
     * @param emailId the email id
     * @return the all group manager for owner
     */
    public List<BookMarkUrlGroupResponse> getAllGroupManagerForOwner(String emailId) {
        List<BookMarkUrlGroupResponse> bookMarkUrlGroupResponseList = getAllGroupManager(emailId);
        return bookMarkUrlGroupResponseList.stream().filter(bookMarkUrlGroupResponse -> bookMarkUrlGroupResponse.getHasAdmin() == true).collect(Collectors.toList());
    }


    /**
     * Add card to group manager card in group.
     *
     * @param bookMarkUrlCardInGroupRequest the card in group request
     * @return the card in group
     */
    public BookMarkUrlCardInGroup addCardToGroupManager(BookMarkUrlCardInGroupRequest bookMarkUrlCardInGroupRequest) {
        BookMarkUrlGroup bookMarkUrlGroup = getGroupById(bookMarkUrlCardInGroupRequest.getGroup_id());
        Collection<BookMarkUrlCardInGroup> bookMarkUrlCardInGroups =  bookMarkUrlCardInGroupRepository.fetchAllCardByGroupId(bookMarkUrlGroup.getId());
        if(bookMarkUrlCardInGroups.isEmpty()) {
            if(bookMarkUrlGroup.getCreated_by().equals(bookMarkUrlCardInGroupRequest.getAdded_by())) {
                BookMarkUrlCardInGroup bookMarkUrlCardInGroup = saveCardInGroup(bookMarkUrlCardInGroupRequest);
                bookMarkUrlUrlRepository.updateUrlExpireDate(null, bookMarkUrlCardInGroup.getCard_id());
                return bookMarkUrlCardInGroup;
            }
            else {
                throw new BookMarkUrlForbiddenException("Since, you are not part of this bookMarkUrlGroup, You are not authorise to perform this action.");
            }
        }
        else {
            userInGroupRepository.findAllUserInGroupByGroupID(bookMarkUrlGroup.getId()).stream().map(bookMarkUrlUserInGroup -> {
                if (bookMarkUrlUserInGroup.getEmail().equals(bookMarkUrlCardInGroupRequest.getAdded_by())) {
                    return saveCardInGroup(bookMarkUrlCardInGroupRequest);
                }
                return null;
            });
        }
        return null;
    }


    private BookMarkUrlCardInGroup saveCardInGroup(BookMarkUrlCardInGroupRequest bookMarkUrlCardInGroupRequest) {
        BookMarkUrlCardInGroup bookMarkUrlCardInGroup = new BookMarkUrlCardInGroup();
        bookMarkUrlCardInGroup.setCard_id(bookMarkUrlCardInGroupRequest.getCard_id());
        bookMarkUrlCardInGroup.setGroup_id(bookMarkUrlCardInGroupRequest.getGroup_id());
        bookMarkUrlCardInGroup.setAdded_by(bookMarkUrlCardInGroupRequest.getAdded_by());
        bookMarkUrlCardInGroup.setAdded_date(new Date());
        bookMarkUrlCardInGroupRepository.save(bookMarkUrlCardInGroup);
        return bookMarkUrlCardInGroup;
    }

    /**
     * Remove card to group manager object.
     *
     * @param bookMarkUrlCardInGroupRequest the card in group request
     * @return the object
     */
    public Object removeCardToGroupManager(BookMarkUrlCardInGroupRequest bookMarkUrlCardInGroupRequest) {
        BookMarkUrlGroup bookMarkUrlGroup = getGroupById(bookMarkUrlCardInGroupRequest.getGroup_id());
        Collection<BookMarkUrlCardInGroup> bookMarkUrlCardInGroups =  bookMarkUrlCardInGroupRepository.fetchAllCardByGroupId(bookMarkUrlGroup.getId());
        if(bookMarkUrlCardInGroups.isEmpty()) {
            return null;
        }
        else {
            userInGroupRepository.findAllUserInGroupByGroupID(bookMarkUrlGroup.getId()).stream().map(bookMarkUrlUserInGroup -> {
                if (bookMarkUrlUserInGroup.getEmail().equals(bookMarkUrlCardInGroupRequest.getAdded_by())) {
                    bookMarkUrlCardInGroups.stream().map(bookMarkUrlCardInGroup -> {
                        bookMarkUrlCardInGroupRepository.deleteCardInGroup(bookMarkUrlCardInGroup.getCard_id());
                        bookMarkUrlUrlRepository.updateUrlExpireDate(new Date(), bookMarkUrlCardInGroup.getCard_id());
                        return null;
                    });
                }
                return null;
            });
        }

        return null;
    }

    /**
     * Gets all card for group manager.
     *
     * @param groupId the group id
     * @return the all card for group manager
     */
    public List<BookMarkUrlCard> getAllCardForGroupManager(int groupId) {
        List<BookMarkUrlCard> bookMarkUrlCards = new ArrayList<>();
        Collection<BookMarkUrlCardInGroup> bookMarkUrlCardInGroups =  bookMarkUrlCardInGroupRepository.fetchAllCardByGroupId(groupId);
        bookMarkUrlCardInGroups.stream().forEach(bookMarkUrlCardInGroup -> bookMarkUrlCards.addAll(bookMarkUrlCardRepository.findAllById(Collections.singleton(bookMarkUrlCardInGroup.getCard_id()))));
        return bookMarkUrlCards;
    }
}
