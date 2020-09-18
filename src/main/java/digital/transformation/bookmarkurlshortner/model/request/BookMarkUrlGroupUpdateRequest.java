package digital.transformation.bookmarkurlshortner.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * The type BookMarkUrlGroup update request.
 */
@Getter
@Setter
public class BookMarkUrlGroupUpdateRequest {

    private int id;
    private String name;
    private String description;
    private String tribe;
    private String team;
    private String component;
    private String updated_by;
}
