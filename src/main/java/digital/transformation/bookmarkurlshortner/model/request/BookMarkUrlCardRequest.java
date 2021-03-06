package digital.transformation.bookmarkurlshortner.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type BookMarkUrlCard request.
 */
@Getter
@Setter
public class BookMarkUrlCardRequest {
    private String title;
    private String description;
    private String original_url;
    private Date expire_date;
    private String tribe;
    private String team;
    private String component;
    private String created_by;
    private String updated_by;
}
