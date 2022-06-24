package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.LibService;
import lee.hawoob.finalproject.service.MbtiService;
import lee.hawoob.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LibService libService;

    // 메인화면 접근 시 내 서재에 담긴 수를 기준으로 원하는 순위만큼 상위의 도서데이터와 서재에 담긴 수를 화면에 전달
    @GetMapping()
    public String gotohome(Model model) {

        // 상위 3건의 도서데이터 담기
        Map<Lib, Integer> top3Books = libService.combineBookMap(0,3);
        // 상위 4위부터 10위까지의 도서데이터 담기
        Map<Lib, Integer> top4to10Books = libService.combineBookMap(3,10);
        // 상위 9건의 MBTI 데이터 담기
        Map<String, Integer> top9Mbti = libService.findTopMbti(9);

        model.addAttribute("top9Mbti", top9Mbti);
        model.addAttribute("top3Books", top3Books);
        model.addAttribute("top4to10Books", top4to10Books);

        return "/home";
    }

    // 로그인 시 가입이력이 있다면 홈화면으로 이동, 가입 이력이 없다면 MBTI 및 닉네임 등록화면으로 이동
    @GetMapping("/mbtitest")
    public String mbtitest(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        httpSession.getAttribute("user");
        String email = principalDetails.getUser().getEmail();

        if(httpSession.getAttribute("user") == null) {
            model.addAttribute("email", email);
            return "/signup";
        } else {
            return "redirect:/home";
        }
    }

    // 로그인 후 이용가능한 화면 접근 시 메세지 출력
    @GetMapping("/message")
    public String message(Model model){

        model.addAttribute("message", "로그인 후 이용가능합니다.");
        model.addAttribute("url", "/oauth2/authorization/naver");

        return "/message";
    }

}
