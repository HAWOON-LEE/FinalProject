package lee.hawoob.finalproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK_INFO")
@Data
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MBTI", nullable = false)
    private Mbti mbti;


//    @OneToMany(mappedBy = "isbn", cascade = CascadeType.ALL)
//    List<Lib> isbnList = new ArrayList<>();
}
