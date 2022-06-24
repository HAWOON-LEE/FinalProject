package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.form.ReviewForm;
import lee.hawoob.finalproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 상단 태그나 내 서재에서 특정도서 한줄평 작성하기 클릭을 통해 접근 시, 로그인된 사용자의 서재에 담긴 도서 정보와, 사용자의 입력정보를 받아올 form객체를 화면에 전달
    @GetMapping("")
    public String gotoWriteReview(ReviewForm reviewForm, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        List<Lib> libs = reviewService.findByNickname(principalDetails);

        model.addAttribute("reviewForm", reviewForm);
        model.addAttribute("libs", libs);

        return "review";
    }

    // 한줄평 등록 시 입력 데이터 검증 후, 확인 메세지 출력 및 등록
    @PostMapping("/enroll")
    public String saveReview(@ModelAttribute @Validated ReviewForm reviewForm,
                             BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        if(bindingResult.hasErrors()) {

            List<Lib> libs = reviewService.findByNickname(principalDetails);

            model.addAttribute("reviewForm", reviewForm);
            model.addAttribute("libs", libs);

            return "review";
        }

        reviewService.saveReview(reviewForm, principalDetails);
        System.out.println("저장완료");

        List<Lib> libs = reviewService.findByNickname(principalDetails);

        model.addAttribute("message", "한줄평이 등록되었습니다.");
        model.addAttribute("url", "/review");
        model.addAttribute("reviewForm", reviewForm);
        model.addAttribute("libs", libs);

        return "message";
    }
}
