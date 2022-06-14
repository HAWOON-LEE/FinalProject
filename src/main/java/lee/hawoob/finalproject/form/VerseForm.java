package lee.hawoob.finalproject.form;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.User;
import lombok.Data;

@Data
public class VerseForm {

    private String nickname;

    private String verse;

    private String sub;

    private int page;

    private String isbn;

    private User user;

    private Book book;
}
