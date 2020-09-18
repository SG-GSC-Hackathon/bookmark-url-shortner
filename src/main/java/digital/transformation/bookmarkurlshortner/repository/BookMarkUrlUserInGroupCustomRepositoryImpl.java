package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * The type BookMarkUrlUser in group custom repository.
 */
@Repository
@Transactional(readOnly = true)
public class BookMarkUrlUserInGroupCustomRepositoryImpl implements BookMarkUrlUserInGroupCustomRepository {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    public List<BookMarkUrlUserInGroup> findAllUserInGroupByGroupName(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT uig.* FROM bookmarkurlshortner.user_in_group as uig WHERE uig.group_id = ?", BookMarkUrlUserInGroup.class);
        query.setParameter(1, groupName);
        return query.getResultList();
    }

    @Override
    public List<BookMarkUrlUserInGroup> findAllUserInGroupByGroupID(int groupId) {
        Query query = entityManager.createNativeQuery("SELECT uig.* FROM bookmarkurlshortner.user_in_group as uig WHERE uig.group_id = ?", BookMarkUrlUserInGroup.class);
        query.setParameter(1, groupId);
        return query.getResultList();
    }
}
