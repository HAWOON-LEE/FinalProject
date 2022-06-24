package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("recommend")
public class RecommendController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MbtiService mbtiService;

    // 상단메뉴바를 통해 도서추천 화면 첫 접근 시 리스트(ENFJ 도서리스트)
    @GetMapping
    public String showList(Model model) {

        List<Mbti> mbtiList = mbtiService.selectAll();
        List<Book> bookList = bookService.findAllByMbti("enfj");

        model.addAttribute("msg", "ENFJ");
        model.addAttribute("list", mbtiList);
        model.addAttribute("bookList", bookList);

        return "recommend";
    }

    // 각 mbti별 추천도서 리스트
    @GetMapping("/{mbti}")
    public String showListByMbti(Model model, @PathVariable String mbti) {

        List<Mbti> mbtiList = mbtiService.selectAll();
        List<Book> bookList = bookService.findAllByMbti(mbti);

        model.addAttribute("msg", mbti);
        model.addAttribute("list", mbtiList);
        model.addAttribute("bookList", bookList);

        return "recommend";
    }

    // 도서 검색 시 검색 키워드를 포함한 도서 리스트를 출력
     @PostMapping("/search")
    public String searchBookList(Model model, @RequestParam("keyword") String keyword) {

        List<Mbti> list = mbtiService.selectAll();
        List<Book> searchBooks = bookService.findByKeywordContaining(keyword);

        model.addAttribute("list", list);
        model.addAttribute("bookList", searchBooks);

        return "recommend";
    }
}
