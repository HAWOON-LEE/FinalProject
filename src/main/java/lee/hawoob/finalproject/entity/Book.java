package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_INFO")
@Data
public class Book {

    @Id
    @Column(name = "BOOK_ISBN", nullable = false)
    private String isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "LINK")
    private String link;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "PUB_DATE", nullable = false)
    private String pubDate;

    @Column(name = "BOOK_DESC")
    private String bookDesc;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MBTI")
    private Mbti mbti;
}
