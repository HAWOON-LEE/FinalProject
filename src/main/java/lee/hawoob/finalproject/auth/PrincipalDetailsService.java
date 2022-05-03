package lee.hawoob.finalproject.auth;

import lee.hawoob.finalproject.dto.UserDto;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

        User userEntity = userService.findEmail(user);
        Optional<User> userKey = userService.findKey(user);

        if(userKey.isEmpty()) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
