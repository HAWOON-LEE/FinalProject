package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
}
