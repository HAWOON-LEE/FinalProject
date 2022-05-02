package lee.hawoob.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ONE_VERSE")
@NoArgsConstructor
@AllArgsConstructor
public class Verse{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VERSE_ID")
    private Long index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME" )
    private User user;

    @Column(name = "VERSE", nullable = false)
    private String verse;

    @Column(name = "SUB")
    private String sub;

    @Column(name = "PAGE")
    private int page;
}
