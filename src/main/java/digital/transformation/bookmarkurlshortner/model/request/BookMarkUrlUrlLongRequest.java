package digital.transformation.bookmarkurlshortner.model.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type BookMarkUrlUrl long request.
 */
@Getter
@Setter
public class BookMarkUrlUrlLongRequest {

    private String longUrl;
    private Date expiresDate;

}
