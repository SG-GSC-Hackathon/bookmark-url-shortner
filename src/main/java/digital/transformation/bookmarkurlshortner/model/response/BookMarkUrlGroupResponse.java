package digital.transformation.bookmarkurlshortner.model.response;

import digital.transformation.bookmarkurlshortner.model.mapper.BookMarkUrlStringListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookMarkUrlGroupResponse {

    private int id;
    private String name;
    private String description;
    private Date created_date;
    private String created_by;
    private String tribe;
    private String team;
    private String component;
    private Date updated_date;
    private String updated_by;
    private Boolean active;
    private Boolean hasAdmin;

    @Convert(converter = BookMarkUrlStringListConverter.class)
    private List<String> admin;
}
