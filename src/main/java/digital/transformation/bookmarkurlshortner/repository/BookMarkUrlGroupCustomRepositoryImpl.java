package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlCardInGroup;
import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * The type BookMarkUrlGroup custom repository.
 */
@Repository
@Transactional(readOnly = true)
public class BookMarkUrlGroupCustomRepositoryImpl implements BookMarkUrlGroupCustomRepository {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    public List<BookMarkUrlGroup> findAllActiveGroup(Boolean flag) {

        Query query = entityManager.createNativeQuery("SELECT gp.* FROM bookmarkurlshortner.group as gp WHERE gp.active = ?", BookMarkUrlGroup.class);
        query.setParameter(1, flag);
        return query.getResultList();
    }

    @Override
    public void removeCardFromGroup(int cardId) {
        Query query = entityManager.createNativeQuery("DELETE FROM bookmarkurlshortner.card_in_group as cig WHERE cig.card_id = ?", BookMarkUrlCardInGroup.class);
        query.setParameter(1, cardId);
        query.getResultList();
    }


}
