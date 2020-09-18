package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type BookMarkUrlUser in group.
 */
@Getter
@Setter
@Entity
@Table(name = "user_in_group", schema = "bookmarkurlshortner")
public class BookMarkUrlUserInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String email;
    private int group_id;
    private Date added_date;
    private String added_by;

    /**
     * Instantiates a new BookMarkUrlUser in group.
     */
    public BookMarkUrlUserInGroup() {

    }

    /**
     * Instantiates a new BookMarkUrlUser in group.
     *
     * @param user     the user
     * @param group_id the group id
     * @param admin    the admin
     */
    public BookMarkUrlUserInGroup(String user, int group_id, String admin) {
        super();
        this.email = user;
        this.group_id = group_id;
        this.added_by = admin;
    }


}
