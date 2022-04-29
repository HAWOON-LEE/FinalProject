package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.UserDto;
import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String gotohome() {

        return "/home";
    }

    @GetMapping("/mbtitest")
    public String mbtitest(UserDto user){

    if(userService.findMbti(user.getMbti()) == null){
        return "/signup";
    }

    return "/home";
    }


    @GetMapping("/passage")
    public String gotopassge(){

        return "/passage";
    }

    @GetMapping("/review")
    public String gotoreview(){
        return "/review";
    }

    @GetMapping("/bookshelf")
    public String gotobookshelf(){ return "/bookshelf"; }

    @GetMapping("/recommend")
    public String gotorecommend(){ return "/recommend"; }

    @GetMapping("/message")
    public String message(Model model){

        model.addAttribute("message", "로그인 후 이용가능합니다.");
        model.addAttribute("url", "/oauth2/authorization/naver");
        return "/message";
    }
}
