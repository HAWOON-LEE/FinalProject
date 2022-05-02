package lee.hawoob.finalproject.form;

import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostForm {


    private String title;

    private String imgURL;

    private String content;
}
