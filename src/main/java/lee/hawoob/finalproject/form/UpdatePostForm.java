package lee.hawoob.finalproject.form;

import lombok.Data;

@Data
public class UpdatePostForm {

    private Long postIndex;

    private String title;

    private String imgURL;

    private String content;
}
