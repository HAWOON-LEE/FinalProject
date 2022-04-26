package lee.hawoob.finalproject.oauth;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.oauth.provider.NaverUserInfo;
import lee.hawoob.finalproject.oauth.provider.OAuth2UserInfo;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //네이버 api의 id 유니크 필드
        if(registrationId.equals("naver")){
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }

        String nickname = oAuth2UserInfo.getEmail().substring(0,oAuth2UserInfo.getEmail().indexOf('@'));
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";
        String key = oAuth2UserInfo.getProviderId();
        System.out.println(key);

        User userEntity = userRepository.findByEmail("email");
        if(userEntity == null){
            System.out.println("최초 로그인");
            userEntity = new User().builder()
                    .email(email)
                    .nickname(nickname)
                    .role(role)
                    .build();
            userRepository.save(userEntity);
        }else {
            System.out.println("로그인 한 적이 있습니다.");
        }

        httpSession.setAttribute("user", userEntity);
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
