package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private LibService libService;

    @RequestMapping("/")
    public String home(Model model) {

        // 상위 3건의 도서데이터 담기
        Map<Lib, Integer> top3Books = libService.combineBookMap(0,3);
        // 상위 4위부터 10위까지의 도서데이터 담기
        Map<Lib, Integer> top4to10Books = libService.combineBookMap(3,10);
        // 상위 9건의 MBTI 데이터 담기
        Map<String, Integer> top9Mbti = libService.findTopMbti(9);

        model.addAttribute("top9Mbti", top9Mbti);
        model.addAttribute("top3Books", top3Books);
        model.addAttribute("top4to10Books", top4to10Books);

        return "home";
    }
}
