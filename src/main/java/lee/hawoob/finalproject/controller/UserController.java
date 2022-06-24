package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.UserDto;
import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.oauth.OAuth2UserService;
import lee.hawoob.finalproject.repository.UserRepository;
import lee.hawoob.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signup(Model model,
                         @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String email = principalDetails.getUser().getEmail();
        model.addAttribute("email", email);
        return "/signup";
    }

    @PostMapping("/signup")
    public String postSignup(UserDto user) {

        userRepository.update(user.getMbti(), user.getNickname(), user.getEmail());
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String getmypage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String email = principalDetails.getUser().getEmail();
        User user = userService.mypageData(email);
        String nickname = user.getNickname();
        String mbti = user.getMbti().getMbti();

        model.addAttribute("email", email);
        model.addAttribute("nickname", nickname);
        model.addAttribute("mbti", mbti);

        return "/mypage";
    }

    @PostMapping("/nicknameCheck2")
    @ResponseBody
    public int nicknameCheck2(@RequestParam("nickname") String nickname) {

        System.out.println(nickname);
        int cnt = userService.nicknameOverlap(nickname);
        System.out.println(cnt);
        return cnt;
    }

    @PostMapping("/mypage")
    public String postmypage(UserDto user) {

        userRepository.update(user.getMbti(), user.getNickname(), user.getEmail());
        return "redirect:/";
    }

}

