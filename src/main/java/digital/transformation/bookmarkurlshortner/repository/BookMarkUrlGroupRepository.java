package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * The interface BookMarkUrlGroup repository.
 */
@Transactional
public interface BookMarkUrlGroupRepository extends JpaRepository<BookMarkUrlGroup, Integer> {

}
