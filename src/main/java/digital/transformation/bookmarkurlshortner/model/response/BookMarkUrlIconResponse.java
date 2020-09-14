package digital.transformation.bookmarkurlshortner.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkUrlIconResponse {

    private int id;
    private String name;
    private String type;
    private int cardId;
    private byte[] file;

    public BookMarkUrlIconResponse(String originalFilename, String contentType, byte[] compressBytes, int cardId, int id) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = compressBytes;
        this.cardId = cardId;
        this.id = id;
    }
}
