package lee.hawoob.finalproject.form;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.User;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class VerseForm {

    @NotBlank(message = "나만의 구절을 입력하세요.")
    private String verse;

    private String sub;

    @Positive(message = "페이지 정보를 확인하세요.")
    private int page;

    private String nickname;

    private String isbn;

    private User user;

    private Book book;
}
