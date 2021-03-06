package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlIcon;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * The interface BookMarkUrlIcon repository.
 */
@Transactional
public interface BookMarkUrlIconRepository extends JpaRepository<BookMarkUrlIcon, Integer> {

}
