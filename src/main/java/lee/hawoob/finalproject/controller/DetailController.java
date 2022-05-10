package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.form.BookForm;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LibService libService;

    @GetMapping("/{id}")
    public String showDetail(Model model, @PathVariable String id) {
        List<Book> book = bookService.findByIsbn(id);

        model.addAttribute("book", book);
        return "detail";
    }

//    @GetMapping("/insert")
//    public String insertBook(@RequestParam("isbn") String isbn,
//                             @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        libService.saveBook(isbn, principalDetails);
//        return "detail";
//    }
}
