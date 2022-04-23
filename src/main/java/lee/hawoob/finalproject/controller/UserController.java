package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.UserDto;
import lee.hawoob.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model,
                         @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(principalDetails);
        String email = principalDetails.getUser().getEmail();
        System.out.println(email);
        model.addAttribute("email", email);

        return "/signup";
    }

    @PostMapping("/signup")
    public String postSignup(UserDto user){

        userRepository.update(user.getMbti(), user.getNickname(), user.getEmail());
        return "/home";
    }
}
