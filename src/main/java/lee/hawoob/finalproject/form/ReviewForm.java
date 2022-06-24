package lee.hawoob.finalproject.form;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ReviewForm {

    @NotBlank(message = "한줄평을 입력하세요.")
    private String review;

    @NotNull(message = "별점을 입력하세요.")
    private Integer rating;

    private String nickname;

    private String isbn;

    private User user;

    private Book book;

}
