package lee.hawoob.finalproject.service;


import lee.hawoob.finalproject.dto.UserDto;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
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

//    public List<UserDto> getUserList(User user) {
//        List<UserDto> list = userRepository.findUserBy(user);
//        UserDto userDto = UserDto.builder()
//                .email(user.getEmail())
//                .nickname(user.getNickname())
//                .mbti(user.getMbti().getMbti())
//                .keyID(user.getKeyID())
//                .build();
//        list.add(userDto);
//
//        return list;
//    }

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







