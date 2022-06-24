package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long boardIndex;

    private String title;

    private Board board;

    private String content;

    private User user;

    private LocalDateTime date;

}