package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
//    @EqualsAndHashCode
public class LibId implements Serializable {

    @Column(name = "BOOK_ISBN")
    private String isbn;

    @Column(name = "NICKNAME")
    private String nickname;
}