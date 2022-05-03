package lee.hawoob.finalproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "닉네임을 입력해주세요")
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