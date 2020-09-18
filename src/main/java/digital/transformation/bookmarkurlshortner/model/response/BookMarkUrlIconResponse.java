package digital.transformation.bookmarkurlshortner.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type BookMarkUrlIcon response.
 */
@Getter
@Setter
public class BookMarkUrlIconResponse {

    private int id;
    private String name;
    private String type;
    private int cardId;
    private byte[] file;

    /**
     * Instantiates a new BookMarkUrlIcon response.
     *
     * @param originalFilename the original filename
     * @param contentType      the content type
     * @param compressBytes    the compress bytes
     * @param cardId           the card id
     * @param id               the id
     */
    public BookMarkUrlIconResponse(String originalFilename, String contentType, byte[] compressBytes, int cardId, int id) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = compressBytes;
        this.cardId = cardId;
        this.id = id;
    }
}
