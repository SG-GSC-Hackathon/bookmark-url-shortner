package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * The interface BookMarkUrlCard repository.
 */
@Transactional
public interface BookMarkUrlCardRepository extends JpaRepository<BookMarkUrlCard, Integer> {

    /**
     * Update address.
     *
     * @param url_id  the url id
     * @param icon_id the icon id
     * @param id      the id
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE BookMarkUrlCard c SET c.url_id = :url_id, c.icon_id = :icon_id WHERE c.id = :id")
    void updateAddress(@Param("url_id") int url_id, @Param("icon_id") int icon_id, @Param("id") int id);
}
