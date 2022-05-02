package lee.hawoob.finalproject.entity;

import lee.hawoob.finalproject.dto.LibDto;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MY_LIBRARY")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@Embeddable
public class Lib extends LibDto {

    @EmbeddedId
    private LibId libId;

//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", insertable = false, updatable = false)
    private Book book;

//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME", referencedColumnName = "NICKNAME", insertable = false, updatable = false)
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lib lib = (Lib) o;
        return Objects.equals(user, lib.user) && Objects.equals(book, lib.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, user);
    }
}
