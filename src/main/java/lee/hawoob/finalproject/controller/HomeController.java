package lee.hawoob.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/mypage")
    public String mypage(){
        return "/mypage";
    }
}
