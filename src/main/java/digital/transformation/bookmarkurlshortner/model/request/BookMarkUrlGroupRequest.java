package digital.transformation.bookmarkurlshortner.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * The type BookMarkUrlGroup request.
 */
@Getter
@Setter
public class BookMarkUrlGroupRequest {

    private String name;
    private String description;
    private String tribe;
    private String team;
    private String component;
    private String created_by;
    private String updated_by;
}
