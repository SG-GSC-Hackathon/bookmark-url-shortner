package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * The interface BookMarkUrlUser repository.
 */
@Transactional
public interface BookMarkUrlUserRepository extends JpaRepository<BookMarkUrlUser, Integer> {

}
