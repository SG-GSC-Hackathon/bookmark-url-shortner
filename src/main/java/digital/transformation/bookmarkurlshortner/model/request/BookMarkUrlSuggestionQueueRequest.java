package digital.transformation.bookmarkurlshortner.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Suggestion queue request.
 */
@Getter
@Setter
public class BookMarkUrlSuggestionQueueRequest {
    private int card_id;
    private String email;
    private String suggestion_text;

}
