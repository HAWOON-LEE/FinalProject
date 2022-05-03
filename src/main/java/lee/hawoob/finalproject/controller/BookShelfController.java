package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookShelfController {

    @Autowired
    private BookService bookService;
}
