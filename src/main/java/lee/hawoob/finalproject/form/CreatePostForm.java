package lee.hawoob.finalproject.form;

import lee.hawoob.finalproject.entity.User;
import lombok.Data;

@Data
public class CreatePostForm {


    private String title;

    private String imgURL;

    private String content;
}
