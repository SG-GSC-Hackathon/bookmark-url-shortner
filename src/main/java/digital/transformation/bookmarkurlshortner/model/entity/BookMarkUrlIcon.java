package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type BookMarkUrlIcon.
 */
@Getter
@Setter
@Entity
@Table(name = "icon", schema = "bookmarkurlshortner")
public class BookMarkUrlIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private String type;
    private int card_id;
    @Lob
    private byte[] file;

    /**
     * Instantiates a new BookMarkUrlIcon.
     */
    public BookMarkUrlIcon() {

    }

    /**
     * Instantiates a new BookMarkUrlIcon.
     *
     * @param originalFilename the original filename
     * @param contentType      the content type
     * @param cardId           the card id
     * @param compressBytes    the compress bytes
     */
    public BookMarkUrlIcon(String originalFilename, String contentType, int cardId, byte[] compressBytes) {
        this.name = originalFilename;
        this.type = contentType;
        this.card_id = cardId;
        this.file = compressBytes;
    }
}
