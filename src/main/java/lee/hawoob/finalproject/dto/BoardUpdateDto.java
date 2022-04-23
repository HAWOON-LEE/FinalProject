package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateDto {

    private String title;

//    private String user;

    private String content;

    private LocalDateTime date;


    public BoardUpdateDto(Board board){
        title = board.getTitle();
//        user = board.getUser().getNickname();
        content = board.getContent();
    }


}
