package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findKey(String keyed) {
        return userRepository.findByKeyID(keyed);
    }

    public User findNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }

    public User mypageData(String email){
        return userRepository.mypage(email);
    }

    public int nicknameOverlap(String nickname) {
        int cnt = userRepository.nicknameCheck(nickname);
        return cnt;
    }

    public User findMbti(String mbti){
        return userRepository.findByMbti(mbti);
    }

    public String logininfo(String keyID){
        return userRepository.findAllByLogininfo(keyID);
    }

}







