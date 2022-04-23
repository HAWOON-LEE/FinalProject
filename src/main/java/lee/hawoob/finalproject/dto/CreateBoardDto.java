package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Board;
//import MBTI.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoardDto {


    private String title;

    private String user;

    private String content;

    private LocalDateTime date;


    public CreateBoardDto(Board board){
        title = board.getTitle();
        user = board.getUser().getNickname();
        content = board.getContent();
    }


//    public record CreateBoardDto(String title, String content){
//        public Post toEntity(){
//            return Post.builder().title(title).content(content).build();
//        }
//    }
}