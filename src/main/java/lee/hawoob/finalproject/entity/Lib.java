package lee.hawoob.finalproject.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MY_LIBRARY")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Lib {

    @EmbeddedId
    private LibId libId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME", nullable = false)
    private User user;
}
