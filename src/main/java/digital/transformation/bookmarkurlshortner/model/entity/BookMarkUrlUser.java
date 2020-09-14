package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user", schema = "bookmarkurlshortner")
@Getter
@Setter
public class BookMarkUrlUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private boolean active;

}
