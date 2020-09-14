package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;

import java.util.List;

public interface BookMarkUrlGroupCustomRepository {

    List<BookMarkUrlGroup> findAllActiveGroup(Boolean flag);

}
