package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="user-in-group", schema = "bookmarkurlshortner")
public class BookMarkUrlUserInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String group_name;
    private Date added_date;
    private String added_by;

    public BookMarkUrlUserInGroup() {

    }

    public BookMarkUrlUserInGroup(String user, String groupName, String admin) {
        super();
        this.email = user;
        this.group_name = groupName;
        this.added_by = admin;
    }


}
