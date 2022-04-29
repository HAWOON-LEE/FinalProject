package lee.hawoob.finalproject.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateBoardForm {

    private Long BoardIndex;

    private String title;

//    private String imgURL;

    private String content;

    private LocalDateTime date;
}
