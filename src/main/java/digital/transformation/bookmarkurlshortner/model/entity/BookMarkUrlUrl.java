package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type BookMarkUrlUrl.
 */
@Getter
@Setter
@Entity
@Table(name = "url", schema = "bookmarkurlshortner")
public class BookMarkUrlUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String long_url;
    private Date created_date;
    private int card_id;
    private String short_url;
    private Date expires_date;
}
