package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "VERSE")
public class Verse {

    @Id
    @Column(name = "VERSE_INDEX")
    private int index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME", nullable = false)
    private User user;

    @Column(name = "PICKED_VERSE", nullable = false)
    private String verse;

    @Column(name = "PAGE_NUM")
    private int page;
}
