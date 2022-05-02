package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bookshelf")
public class BookShelfController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LibService libService;

    @GetMapping
    public String showMyList(Model model, List<Book> books, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String nickname = principalDetails.getUser().getNickname();
        List<Lib> isbns = libService.findAllByNickname(nickname);
        System.out.println(isbns);

        model.addAttribute("books", books);
        return "bookshelf";
    }
}
