package lee.hawoob.finalproject.entity;

import lee.hawoob.finalproject.dto.LibDto;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MY_LIBRARY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lib extends LibDto {

    @EmbeddedId
    private LibId libId = new LibId();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", insertable = false, updatable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME", referencedColumnName = "NICKNAME", insertable = false, updatable = false)
    private User user;

}
