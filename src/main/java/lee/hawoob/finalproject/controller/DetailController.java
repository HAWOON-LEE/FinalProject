package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.form.BookForm;
import lee.hawoob.finalproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public String showDetail(Model model, @PathVariable String id) {
        List<Book> book = bookService.findByIsbn(id);

        model.addAttribute("book", book);
        return "detail";
    }

//    @GetMapping("/insert")
//    public String insertBook(Model model, @RequestParam("isbn") String isbn) {
//
//    }
}
