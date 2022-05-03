package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookshelf")
public class BookShelfController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LibService libService;

    @GetMapping
    public String showMyList(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Lib> books = libService.findAllByNickname(principalDetails);

        List<String> isbns = new ArrayList<>();
        for(int i=0; i<books.size(); i++) {
            String isbn = books.get(i).getBook().getIsbn();
            isbns.add(isbn);
        }

        int cnt = isbns.size();
        model.addAttribute("books", books);
        return "bookshelf";
    }
}
