package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Mbti;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class BookDto {

    private String isbn;

    private String title;

    private String link;

    private String imageUrl;

    private String author;

    private Integer price;

    private String publisher;

    private String pubDate;

    private String bookDesc;

    private Mbti mbti;

    public BookDto(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.link = book.getLink();
        this.imageUrl = book.getImageUrl();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
        this.pubDate = book.getPubDate();
        this.bookDesc = book.getBookDesc();
        this.mbti = book.getMbti();
    }
}
