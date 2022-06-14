package lee.hawoob.finalproject.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentForm {

    private String comment;

    private Long boardIndex;
}
