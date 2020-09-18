package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type BookMarkUrlUser.
 */
@Entity
@Table(name = "user", schema = "bookmarkurlshortner")
@Getter
@Setter
public class BookMarkUrlUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private String email;
    private boolean active;

}
