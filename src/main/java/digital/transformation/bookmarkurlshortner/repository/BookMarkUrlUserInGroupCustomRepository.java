package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;

import java.util.List;

public interface BookMarkUrlUserInGroupCustomRepository {

    List<BookMarkUrlUserInGroup> findAllbyGroup(String groupName);
}
