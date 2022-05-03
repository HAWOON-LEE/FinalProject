package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.BaseTimeEntity;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBoardDto {
    private Long boardIndex;

    private String title;

    private User user;

    private String nickname;

    private String content;

    private LocalDateTime createDate;

    private int view;

    public SearchBoardDto(Board board){
        this.boardIndex = board.getBoardIndex();
        this.title = board.getTitle();
        this.user = board.getUser();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.view = board.getView();
    }

}
