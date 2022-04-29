package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Board;
//import lee.hawoob.finalproject.entity.Comment;
import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long id;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private User user;
    private Board board;

    /* Dto -> Entity */
//    public Comment toEntity() {
//        Comment comments = Comment.builder()
//                .id(id)
//                .comment(comment)
//                .createdDate(createdDate)
//                .modifiedDate(modifiedDate)
//                .user(user)
//                .board(board)
//                .build();
//
//        return comments;
//    }
}