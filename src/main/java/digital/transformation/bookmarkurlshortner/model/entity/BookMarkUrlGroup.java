package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type BookMarkUrlGroup.
 */
@Getter
@Setter
@Entity
@Table(name = "group", schema = "bookmarkurlshortner")

public class BookMarkUrlGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private String description;
    private String tribe;
    private String team;
    private String component;
    private String created_by;
    private String updated_by;
    private Date created_date;
    private Date updated_date;
    private Boolean active;
}
