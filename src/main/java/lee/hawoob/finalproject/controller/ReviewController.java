package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.dto.ReviewDto;
import lee.hawoob.finalproject.entity.Review;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.form.ReviewForm;
import lee.hawoob.finalproject.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 리뷰 등록 컨트롤러
 */

@Controller
@RequestMapping("review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @GetMapping("list")
    public ModelAndView review(ReviewDto dto, @AuthenticationPrincipal PrincipalDetails custom, Model model){
        model.addAttribute("dto", dto);
        List<LibDto> libs = service.findByNickname(custom);

        ModelAndView mav = new ModelAndView();
        mav.addObject("libs", libs);
        mav.setViewName("review");

        return mav;
    }
    @PostMapping("enroll")
    public String addReview(@ModelAttribute ReviewForm form, @AuthenticationPrincipal PrincipalDetails custom, Model model){
        model.addAttribute("dto", form);
        model.addAttribute("libs", form);

        service.saveReview(form, custom);

        return "redirect:/review/list";
    }
}
