package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findEmail(String email){
        return userRepository.findByEmail(email);
    }


}
