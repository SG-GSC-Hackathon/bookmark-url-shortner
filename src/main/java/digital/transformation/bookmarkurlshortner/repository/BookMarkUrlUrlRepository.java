package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

/**
 * The interface BookMarkUrlUrl repository.
 */
@Transactional
public interface BookMarkUrlUrlRepository extends JpaRepository<BookMarkUrlUrl, Integer> {

    /**
     * Update url.
     *
     * @param long_url     the long url
     * @param short_url    the short url
     * @param expires_date the expires date
     * @param id           the id
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE BookMarkUrlUrl u SET u.long_url = :long_url, u.short_url = :short_url, u.expires_date = :expires_date WHERE u.id = :id")
    void updateUrl(@Param("long_url") String long_url, @Param("short_url") String short_url, @Param("expires_date") Date expires_date, @Param("id") int id);


    /**
     * Update url expire date.
     *
     * @param expires_date the expires date
     * @param card_id      the card id
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE BookMarkUrlUrl u SET u.expires_date = :expires_date WHERE u.card_id = :card_id")
    void updateUrlExpireDate(@Param("expires_date") Date expires_date,@Param("card_id") int card_id);

    /**
     * Fetch url object by card id collection.
     *
     * @param card_id the card id
     * @return the collection
     */
    @Modifying(clearAutomatically = true)
    @Query("SELECT u FROM BookMarkUrlUrl u WHERE u.card_id = :card_id")
    Collection<BookMarkUrlUrl> fetchURLObjectByCardId(@Param("card_id") int card_id);

}
