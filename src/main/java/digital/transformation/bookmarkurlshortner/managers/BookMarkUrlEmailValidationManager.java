package digital.transformation.bookmarkurlshortner.managers;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlCardResponse;
import digital.transformation.bookmarkurlshortner.model.response.BookMarkUrlGroupResponse;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlCardRepository;
import digital.transformation.bookmarkurlshortner.repository.BookMarkUrlGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BookMarkUrlEmailValidationManager {

    @Autowired
    private BookMarkUrlCardRepository bookMarkUrlCardRepository;

    @Autowired
    private BookMarkUrlGroupRepository groupRepository;

    public List<BookMarkUrlCard> getAllCard() {
        return bookMarkUrlCardRepository.findAll();

    }
    public List<BookMarkUrlGroup> getAllGroup() {
        return groupRepository.findAll();
    }
     public Map<String, List> getEmailValidationManager(String emailId) {
        Map<String, List> map = new HashMap();

        List<BookMarkUrlCardResponse> cardList = getAllCard().stream()
                 .collect(Collectors.mapping(p -> new ModelMapper().map(p, BookMarkUrlCardResponse.class), Collectors.toList()));

         List<BookMarkUrlCardResponse> bookMarkUrlCardResponseList = cardList.stream().map(cardResponse2 -> {
             if(cardResponse2.getCreated_by().equals(emailId)) {
                 cardResponse2.setHasAdmin(true);
             }
             else {
                 cardResponse2.setHasAdmin(false);
             }
             return  cardResponse2;
         }).collect(Collectors.toList());


         List<BookMarkUrlGroupResponse> groupList = getAllGroup().stream()
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

         map.put("cards", bookMarkUrlCardResponseList);
         map.put("groups", bookMarkUrlGroupResponseList);
        return map;
     }
}
