package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MbtiService mbtiService;

    // 도서추천 화면 첫 접근 시 리스트(ENFJ 도서목록)
    @GetMapping
    public String showList(Model model) {
        Iterable<Mbti> list = mbtiService.selectAll();
        List<Book> bookList = bookService.findAllByMbti("enfj");

        model.addAttribute("msg", "ENFJ");
        model.addAttribute("list", list);
        model.addAttribute("bookList", bookList);
        return "recommend";
    }

    @GetMapping("/{mbti}")
    public String showMbtiList(Model model, @PathVariable String mbti) {
        Iterable<Mbti> list = mbtiService.selectAll();
        List<Book> bookList = bookService.findAllByMbti(mbti);

        model.addAttribute("msg", mbti);
        model.addAttribute("list", list);
        model.addAttribute("bookList", bookList);
        return "recommend";
    }
}
