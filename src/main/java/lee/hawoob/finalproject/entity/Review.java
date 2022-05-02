package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@Data
public class Review {

    @Id
    @Column(name = "REVIEW_INDEX")
    private int index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ISBN", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NICKNAME", nullable = false)
    private User user;

    @Column(name = "REVIEW", nullable = false)
    private String review;
//, nullable = false 별점에도 넣나?
    @Column(name = "RATING")
    private int rating;
}
