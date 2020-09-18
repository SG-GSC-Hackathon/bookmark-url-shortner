package digital.transformation.bookmarkurlshortner.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type BookMarkUrlCard update request.
 */
@Getter
@Setter
public class BookMarkUrlCardUpdateRequest {
    private int id;
    private String title;
    private String description;
    private String original_url;
    private Date expire_date;
    private String tribe;
    private String team;
    private String component;
    private String updated_by;
}
