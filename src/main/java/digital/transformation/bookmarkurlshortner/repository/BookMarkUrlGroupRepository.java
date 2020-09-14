package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookMarkUrlGroupRepository extends JpaRepository<BookMarkUrlGroup, Integer>, BookMarkUrlGroupCustomRepository {

}
