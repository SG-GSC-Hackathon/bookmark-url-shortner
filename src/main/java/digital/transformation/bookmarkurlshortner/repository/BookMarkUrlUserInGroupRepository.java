package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkUrlUserInGroupRepository extends JpaRepository<BookMarkUrlUserInGroup, Integer>, BookMarkUrlUserInGroupCustomRepository {


}
