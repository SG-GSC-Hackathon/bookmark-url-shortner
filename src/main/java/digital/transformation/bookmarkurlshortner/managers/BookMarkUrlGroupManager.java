package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import digital.transformation.bookmarkurlshortner.model.mapper.BookMarkUrlStringListConverter;
import digital.transformation.bookmarkurlshortner.model.request.BookMarkUrlGroupRequest;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlGroupResponse;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlGroupRepository;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlUserInGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMarkUrlGroupManager {

    @Autowired
    private BookMarkUrlGroupRepository groupRepository;

    @Autowired
    private BookMarkUrlUserInGroupRepository userInGroupRepository;

    public BookMarkUrlGroup getGroup(Integer cardId) {
        Optional<BookMarkUrlGroup> card = groupRepository.findById(cardId);
        return card.get();
    }


    public List<BookMarkUrlGroup> findAllActiveGroup() {
        return groupRepository.findAllActiveGroup(true);
    }

    public BookMarkUrlGroupResponse createGroup(BookMarkUrlGroupRequest bookMarkUrlGroupRequest) {

        ModelMapper modelMapper = new ModelMapper();
        BookMarkUrlGroup bookMarkUrlGroup = modelMapper.map(bookMarkUrlGroupRequest, BookMarkUrlGroup.class);
        bookMarkUrlGroup.setCreated_date(new Date());
        bookMarkUrlGroup.setUpdated_date(new Date());
        bookMarkUrlGroup.setActive(true);

        ModelMapper resModelMapper = new ModelMapper();
        BookMarkUrlGroupResponse bookMarkUrlGroupResponse =resModelMapper.map(groupRepository.save(bookMarkUrlGroup), BookMarkUrlGroupResponse.class);
        bookMarkUrlGroupResponse.setHasAdmin(true);

        BookMarkUrlStringListConverter bookMarkUrlStringListConverter = new BookMarkUrlStringListConverter();
        bookMarkUrlGroupResponse.setAdmin(bookMarkUrlStringListConverter.convertToEntityAttribute(bookMarkUrlGroup.getAdmin()));
        return bookMarkUrlGroupResponse;
    }

    public BookMarkUrlGroup getGroupById(Integer cardId) {
        Optional<BookMarkUrlGroup> group = groupRepository.findById(cardId);
        return group.get();
    }

    public void deleteGroup(Integer groupId) {
        groupRepository.deleteById(groupId);
    }

    public void addUserToGroup(String user, String groupName, String admin) {
        BookMarkUrlUserInGroup bookMarkUrlUserInGroup = new BookMarkUrlUserInGroup(user, groupName, admin);
        bookMarkUrlUserInGroup.setAdded_date(new Date());
        userInGroupRepository.save(bookMarkUrlUserInGroup);
    }

    public void removeUserToGroup(String user, String groupName, String admin) {
        userInGroupRepository.findAllbyGroup(groupName).stream().map(bookMarkUrlUserInGroup -> {
            if(bookMarkUrlUserInGroup.getEmail().equals(user)) {
                userInGroupRepository.deleteById(bookMarkUrlUserInGroup.getId());
            }

            return "";
        });
    }

    public List<BookMarkUrlGroupResponse> getAllGroupManager(String emailId) {
        List<BookMarkUrlGroupResponse> groupList = groupRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, BookMarkUrlGroupResponse.class), Collectors.toList()));

        List<BookMarkUrlGroupResponse> bookMarkUrlGroupResponseList = groupList.stream().map(bookMarkUrlGroupResponse -> {
            if(bookMarkUrlGroupResponse.getCreated_by().equals(emailId)) {
                bookMarkUrlGroupResponse.setHasAdmin(true);
            }
            else {
                bookMarkUrlGroupResponse.setHasAdmin(false);
            }
            bookMarkUrlGroupResponse.setAdmin(new ArrayList<>());
            return bookMarkUrlGroupResponse;
        }).collect(Collectors.toList());
        return bookMarkUrlGroupResponseList;
    }
}
