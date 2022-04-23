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

    @Builder
    public UserDto(String nickname, String mbti, String email) {
        this.nickname = nickname;
        this.mbti = mbti;
        this.email = email;
    }
}