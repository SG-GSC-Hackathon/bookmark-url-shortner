package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="url", schema = "bookmarkurlshortner")
public class BookMarkUrlUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String long_url;
    private int card_id;
    private Date created_date;
    private Date expires_date;
}
