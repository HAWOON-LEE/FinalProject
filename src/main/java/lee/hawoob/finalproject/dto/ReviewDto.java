package lee.hawoob.finalproject.dto;


import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Review;
import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto{

    private Long index;

    private String isbn;

    private User user;

    private String nickname;

    private String review;

    private Integer rating;
}
