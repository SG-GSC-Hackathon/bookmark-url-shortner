package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlSuggestionQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * The interface BookMarkUrlGroup repository.
 */
@Transactional
public interface BookMarkUrlSuggestionQueueRepository extends JpaRepository<BookMarkUrlSuggestionQueue, Integer> {

    /**
     * Gets all suggestion queue by card id.
     *
     * @param card_id the card id
     * @return the all suggestion queue by card id
     */
    @Modifying(clearAutomatically = true)
    @Query("SELECT sq FROM BookMarkUrlSuggestionQueue sq WHERE sq.card_id = :card_id")
    Collection<BookMarkUrlSuggestionQueue> getAllSuggestionQueueByCardId(@Param("card_id") int card_id);

    /**
     * Delete suggestion queue by card id.
     *
     * @param card_id the card id
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM BookMarkUrlSuggestionQueue sq WHERE sq.card_id = :card_id")
    void deleteSuggestionQueueByCardId( @Param("card_id") int card_id);

}
