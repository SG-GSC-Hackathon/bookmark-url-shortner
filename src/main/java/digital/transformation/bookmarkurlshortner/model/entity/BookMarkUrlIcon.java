package digital.transformation.bookmarkurlshortner.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="icon", schema = "bookmarkurlshortner")
public class BookMarkUrlIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String type;
    private int cardId;
    @Lob
    private byte[] file;

    public BookMarkUrlIcon() {

    }

    public BookMarkUrlIcon(String originalFilename, String contentType, byte[] compressBytes, int cardId) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = compressBytes;
        this.cardId = cardId;
    }
}
