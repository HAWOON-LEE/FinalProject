package lee.hawoob.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LibId implements Serializable {

    @Column(name = "BOOK_ISBN")
    private String isbn;

    @Column(name = "NICKNAME")
    private String nickname;

}