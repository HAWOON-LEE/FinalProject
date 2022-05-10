package lee.hawoob.finalproject.dto;


import lee.hawoob.finalproject.entity.Review;
import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Data
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto extends Review {

    private Long index;

    private String isbn;

    private User user;

    private String nickname;

    private String review;

    private String sub;

    private int rating;
}
