package digital.transformation.bookmarkurlshortner.repository;

import digital.transformation.bookmarkurlshortner.model.entity.BookMarkUrlUserInGroup;

import java.util.List;

/**
 * The interface BookMarkUrlUser in group custom repository.
 */
public interface BookMarkUrlUserInGroupCustomRepository {

    /**
     * Find all user in group by group name list.
     *
     * @param groupName the group name
     * @return the list
     */
    List<BookMarkUrlUserInGroup> findAllUserInGroupByGroupName(String groupName);

    /**
     * Find all user in group by group id list.
     *
     * @param groupId the group id
     * @return the list
     */
    List<BookMarkUrlUserInGroup> findAllUserInGroupByGroupID(int groupId);
}
