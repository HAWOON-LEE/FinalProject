package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.UserDto;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.UserRepository;
import lee.hawoob.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.HashMap;

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

    @GetMapping("/nicknameCheck1")
    public String nicknameCheck1(UserDto user, Model model) {

        String nickname = user.getNickname();
        boolean result = userService.nicknameOverlap(nickname);

        if (result) {
            System.out.println("사용중");
            model.addAttribute("message", "사용중인 닉네임입니다.");
            model.addAttribute("url", "/signup");
            return "/message";

        } else {
            System.out.println("새 닉네임");
            model.addAttribute("message", "사용가능한 닉네임입니다.");
            model.addAttribute("url", "/signup");
            return "/message";
        }

    }

    @PostMapping("/signup")
    public String postSignup(UserDto user) {

        userRepository.update(user.getMbti(), user.getNickname(), user.getEmail());
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String getmypage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String email = principalDetails.getUser().getEmail();
        String nickname = principalDetails.getUser().getNickname();
        String mbti = String.valueOf(principalDetails.getUser().getMbti());

        model.addAttribute("email", email);
        model.addAttribute("nickname", nickname);
        model.addAttribute("mbti", mbti);

        return "/mypage";
    }

    @GetMapping("/nicknameCheck2")
    public String nicknameCheck2(UserDto user, Model model) {

        String nickname = user.getNickname();
        boolean result = userService.nicknameOverlap(nickname);

        if (result) {
            System.out.println("사용중");
            model.addAttribute("message", "사용중인 닉네임입니다.");
            model.addAttribute("url", "/mypage");
            return "/message";

        } else {
            System.out.println("새 닉네임");
            model.addAttribute("message", "사용가능한 닉네임입니다.");
            model.addAttribute("url", "/mypage");
            return "/message";
        }

    }

    @PostMapping("/mypage")
    public String postmypage(UserDto user) {

        userRepository.update(user.getMbti(), user.getNickname(), user.getEmail());
        return "redirect:/mypage";
    }
}

