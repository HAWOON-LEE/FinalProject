package lee.hawoob.finalproject.form;

import lombok.Data;

@Data
public class UserForm {

    private String nickname;
    private String mbti;
    private String email;

    public UserForm(String nickname, String mbti, String email) {
        this.nickname = nickname;
        this.mbti = mbti;
        this.email = email;
    }
}
