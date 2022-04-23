package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.BaseTimeEntity;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBoardDto extends Board {
//implements SearchInterface
    private Long boardIndex;

    private String title;

    @Nullable
    private User user;

    private String content;

    private LocalDateTime createDate;


    public SearchBoardDto(Board board){
        this.boardIndex = board.getBoardIndex();
        this.title = board.getTitle();
        this.user = board.getUser();
        this.createDate = board.getCreatedDate();
    }

}