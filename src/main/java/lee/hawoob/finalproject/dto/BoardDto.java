package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Data
@NoArgsConstructor
public class BoardDto {

    private Long boardIndex;

    private String title;

    private String imgURL;

    private String content;

    private User user;

    private LocalDateTime date;


    public BoardDto(Board board){
        this.boardIndex = board.getBoardIndex();
        this.title = board.getTitle();
        this.imgURL = board.getImgURL();
        this.content = board.getContent();
        this.user = board.getUser();

    }
}