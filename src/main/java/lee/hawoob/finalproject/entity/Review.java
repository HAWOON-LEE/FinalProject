package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_INDEX")
    private Long index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME", nullable = false)
    private User user;

    @Column(name = "REVIEW", nullable = false)
    private String review;

    @Column(name = "RATING")
    private int rating;

    @Column(name="SUB")
    private String sub;
}
