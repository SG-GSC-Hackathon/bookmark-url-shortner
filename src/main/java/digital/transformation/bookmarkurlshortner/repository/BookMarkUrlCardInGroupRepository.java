package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCardInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * The interface BookMarkUrlCard in group repository.
 */
@Transactional
public interface BookMarkUrlCardInGroupRepository extends JpaRepository<BookMarkUrlCardInGroup, Integer> {

    /**
     * Delete card in group.
     *
     * @param card_id the card id
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM BookMarkUrlCardInGroup as cig WHERE cig.card_id = :card_id")
    void deleteCardInGroup( @Param("card_id") int card_id);

    /**
     * Fetch all card by group id collection.
     *
     * @param group_id the group id
     * @return the collection
     */
    @Modifying(clearAutomatically = true)
    @Query("SELECT cig FROM BookMarkUrlCardInGroup cig WHERE cig.group_id = :group_id")
    Collection<BookMarkUrlCardInGroup> fetchAllCardByGroupId(@Param("group_id") int group_id);

    /**
     * Fetch grup by card id collection.
     *
     * @param card_id the card id
     * @return the collection
     */
    @Modifying(clearAutomatically = true)
    @Query("SELECT cig FROM BookMarkUrlCardInGroup cig WHERE cig.card_id = :card_id")
    Collection<BookMarkUrlCardInGroup> fetchGrupByCardId(@Param("card_id") int card_id);
}
