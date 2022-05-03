package lee.hawoob.finalproject.form;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.User;
import lombok.Data;

@Data
public class VerseForm {

    private Long index;

    private String nickname;

    private String verse;

    private String sub;

    private int page;

    private Book book;

    private User user;
}
