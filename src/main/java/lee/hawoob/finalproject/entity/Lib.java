package lee.hawoob.finalproject.entity;

import lombok.*;
import org.aspectj.asm.internal.Relationship;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MY_LIBRARY")
@Data
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Lib {
//implements Serializable
    @EmbeddedId
    private LibId libId;

    @MapsId("isbn")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN")
    private Book book;

    @MapsId("nickname")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME", referencedColumnName = "NICKNAME")
    private User user;


}
