package digital.transformation.bookmarkurlshortner.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * The type BookMarkUrlCard in group.
 */
@Getter
@Setter
public class BookMarkUrlCardInGroupRequest {

    private int card_id;
    private int group_id;
    private String added_by;

    /**
     * Instantiates a new BookMarkUrlCard in group.
     */
    public BookMarkUrlCardInGroupRequest() {

    }


}
