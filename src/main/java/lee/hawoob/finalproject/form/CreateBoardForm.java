package lee.hawoob.finalproject.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoardForm {

    private String title;

    private String imgURL;

    private String content;
}
