package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type BookMarkUrlCard in group.
 */
@Getter
@Setter
@Entity
@Table(name = "card_in_group", schema = "bookmarkurlshortner")
public class BookMarkUrlCardInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private int card_id;
    private int group_id;
    private Date added_date;
    private String added_by;

    /**
     * Instantiates a new BookMarkUrlCard in group.
     */
    public BookMarkUrlCardInGroup() {

    }


}
