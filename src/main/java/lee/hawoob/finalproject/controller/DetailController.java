package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private BookService bookService;

//    @GetMapping("/{id}")
//    public String showDetail(Model model, @PathVariable String isbn) {
//
//    }
}
