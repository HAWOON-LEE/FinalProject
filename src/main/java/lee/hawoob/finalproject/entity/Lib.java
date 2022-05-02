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

}
