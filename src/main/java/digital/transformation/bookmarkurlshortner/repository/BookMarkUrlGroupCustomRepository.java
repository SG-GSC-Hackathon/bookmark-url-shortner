package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;

import java.util.List;

/**
 * The interface BookMarkUrlGroup custom repository.
 */
public interface BookMarkUrlGroupCustomRepository {

    /**
     * Find all active group list.
     *
     * @param flag the flag
     * @return the list
     */
    List<BookMarkUrlGroup> findAllActiveGroup(Boolean flag);

    /**
     * Remove card from group.
     *
     * @param cardId the card id
     */
    void removeCardFromGroup(int cardId);

}
