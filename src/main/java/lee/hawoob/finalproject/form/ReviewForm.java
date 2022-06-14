package lee.hawoob.finalproject.form;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.User;
import lombok.Data;

@Data
public class ReviewForm {

    private String nickname;

    private String review;

    private String sub;

    private int rating;

    private String isbn;

    private User user;

    private Book book;
}
