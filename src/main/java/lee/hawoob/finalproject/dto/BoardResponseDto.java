package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Board;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
 */

@Getter
public class BoardResponseDto {

    private Long boardIndex;
    private String title;
    private String nickname;
    private String content;
    private String createdDate, modifiedDate;
    private Long view;
    private Long userId;
//    private List<CommentResponseDto> comments;

// DATE를 LOCALDATETIME으로?

    /* Entity -> Dto*/
    public BoardResponseDto(Board board) {
        this.boardIndex = board.getBoardIndex();
        this.title = board.getTitle();
        this.nickname = board.getUser().getNickname();
        this.content = board.getContent();
        this.createdDate = board.getCreateDate().toString();
        this.modifiedDate = board.getModifiedDate().toString();
        this.view = board.getView();
        this.userId = board.getUser().getUser_id();
//        this.comments = board.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}