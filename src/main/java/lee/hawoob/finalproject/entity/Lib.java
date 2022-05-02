package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "LIBRARY")
@Data
public class Lib {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", nullable = false)
    private Book isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
