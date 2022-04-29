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
//@Builder
public class SearchBoardDto extends Board{
    private Long boardIndex;

    private String title;

    private User user;

    private String nickname;

    private String content;

    private LocalDateTime createDate;

    private Long view;


    public SearchBoardDto(Board board){
        this.boardIndex = board.getBoardIndex();
        this.title = board.getTitle();
        this.user = board.getUser();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.view = board.getView();
    }

//    public static SearchBoardDto from(Board board){
//        return SearchBoardDto.builder()
//                .boardIndex(board.getBoardIndex())
//                .nickname(board.getUser().getNickname())
//                .title(board.getTitle())
//                .content(board.getContent())
//                .view(board.getView())
//                .build();
//    }

}
