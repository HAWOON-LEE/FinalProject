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

    private int inStocks;
}
