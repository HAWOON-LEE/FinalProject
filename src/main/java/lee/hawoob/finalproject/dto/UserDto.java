package lee.hawoob.finalproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {


    private String nickname;
    private String mbti;
    private String email;
    private String keyID;

    @Builder
    public UserDto(String nickname, String mbti, String email, String keyID) {
        this.nickname = nickname;
        this.mbti = mbti;
        this.email = email;
        this.keyID = keyID;
    }
}