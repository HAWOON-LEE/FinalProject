package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String gotohome(Model model) {

        return "/home";
    }
    @GetMapping("/passage")
    public String gotopassge() { return "/passage"; }

    @GetMapping("/review")
    public String gotoreview(){
        return "/review";
    }

    @GetMapping("/bookshelf")
    public String gotobookshelf(){ return "/bookshelf"; }

    @GetMapping("/recommend")
    public String gotorecommend(){ return "/recommend"; }


    @GetMapping("/message")
    public ModelAndView message(ModelAndView model){
        model.addObject("message", "로그인 후 이용가능합니다.");
        model.addObject("searchUrl", "/");
        model.setViewName("/message");

        return model;
    }
}
