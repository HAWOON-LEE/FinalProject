package lee.hawoob.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String gotohome(){
        return "/home";
    }

    @GetMapping("/passage")
    public String gotopassge() { return "/passage"; }

    @GetMapping("/review")
    public String gotoreview() { return "/review"; }

    @GetMapping("/bookshelf")
    public String gotobookshelf(){ return "/bookshelf"; }

    @GetMapping("/recommend")
    public String gotorecommend(){ return "/recommend"; }


}
