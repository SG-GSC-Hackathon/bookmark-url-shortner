package digital.transformation.bookmarkurlshortner.model.request;

import digital.transformation.bookmarkurlshortner.model.mapper.BookMarkUrlStringListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.List;

@Getter
@Setter
public class BookMarkUrlGroupRequest {

    private String name;
    private String description;
    private String created_by;
    private String tribe;
    private String team;
    private String component;
    private String updated_by;

    @Convert(converter = BookMarkUrlStringListConverter.class)
    private List<String> admin;
}
