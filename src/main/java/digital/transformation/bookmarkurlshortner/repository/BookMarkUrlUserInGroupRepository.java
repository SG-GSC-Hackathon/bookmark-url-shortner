package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * The interface BookMarkUrlUser in group repository.
 */
@Transactional
public interface BookMarkUrlUserInGroupRepository extends JpaRepository<BookMarkUrlUserInGroup, Integer>, BookMarkUrlUserInGroupCustomRepository {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM BookMarkUrlUserInGroup as uig WHERE uig.email = :email")
    void deleteUserfromGroup( @Param("email") String email);
}
