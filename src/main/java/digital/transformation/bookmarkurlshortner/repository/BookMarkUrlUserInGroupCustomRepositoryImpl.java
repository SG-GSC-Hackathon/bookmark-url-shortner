package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class BookMarkUrlUserInGroupCustomRepositoryImpl implements BookMarkUrlUserInGroupCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<BookMarkUrlUserInGroup> findAllbyGroup(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT uig.* FROM digital.user-in-group as uig WHERE uig.group_name = ?", BookMarkUrlUserInGroup.class);
        query.setParameter(1, groupName);
        return query.getResultList();
    }
}
