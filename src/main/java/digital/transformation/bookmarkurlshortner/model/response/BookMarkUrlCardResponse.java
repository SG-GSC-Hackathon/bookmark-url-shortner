package digital.transformation.bookmarkurlshortner.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type BookMarkUrlCard response.
 */
@Getter
@Setter
public class BookMarkUrlCardResponse {
    private int id;
    private String title;
    private String description;
    private String original_url;
    private String short_url;
    private Date expire_date;
    private String tribe;
    private String team;
    private String component;
    private String created_by;
    private String updated_by;
    private Date created_date;
    private Date updated_date;
    private Boolean active;
    private Boolean hasAdmin;
}

