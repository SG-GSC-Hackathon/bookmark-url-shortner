package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class BookMarkUrlGroupCustomRepositoryImpl implements BookMarkUrlGroupCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<BookMarkUrlGroup> findAllActiveGroup(Boolean flag) {

        Query query = entityManager.createNativeQuery("SELECT gp.* FROM digital.group as gp WHERE gp.active = ?", BookMarkUrlGroup.class);
        query.setParameter(1, flag);
        return query.getResultList();
    }

}
