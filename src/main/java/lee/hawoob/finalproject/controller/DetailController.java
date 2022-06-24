package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.*;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.LibService;
import lee.hawoob.finalproject.service.ReviewService;
import lee.hawoob.finalproject.service.VerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("detail")
public class DetailController {

    @Autowired
    private BookService bookService;
    @Autowired
    private LibService libService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private VerseService verseService;

    // 도서 상세보기 클릭
    @GetMapping("{id}")
    public String showDetail(Model model, @PathVariable String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 상세보기 도서 정보
        Book book = bookService.findByIsbn(id);
        // 내 서재에 담긴 수
        Integer count = libService.findBookCount(id);

        if(count==null) {
            count = 0;
        } else {
            count = libService.findBookCount(id);
        }
        // 구절 목록
        List<Verse> verseList = verseService.findAllVerseByIsbn(id);
        // 한줄평 목록
        List<Review> reviewList = reviewService.findAllReviewByIsbn(id);
        // 한줄평 별 별점 정보
        Map<Long, String> starMap = reviewService.makeStarMap(id);
        // 평균 별점(점수)
        Integer avgRating = reviewService.calculateRating(id);
        // 평균 별점(별)
        String avgStar = reviewService.convertToStar(avgRating);
        // 한줄평&나만의구절 삭제버튼 활성화용 현재 로그인된 회원 닉네임 정보
        String user = "";
        // NullPointExeption 방지
        if(principalDetails!=null) {
            user = principalDetails.getUser().getNickname();
        } else {
            user = "";
        }

        model.addAttribute("verses", verseList);
        model.addAttribute("avgStar", avgStar);
        model.addAttribute("starMap", starMap);
        model.addAttribute("reviews", reviewList);
        model.addAttribute("count", count);
        model.addAttribute("book", book);
        model.addAttribute("user", user);

        return "detail";
    }

    // 내 서재 보관 클릭 시 보관 유무 확인 후 보관
    @PostMapping("insert")
    @ResponseBody
    public String insertBook(@RequestParam("isbn") String isbn, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String resultmsg = "";

        if (libService.searchLib(isbn, principalDetails) == true) {
            resultmsg = "<script>alert('이미 보관된 도서입니다.');history.go(-1)</script>";
        } else {
            libService.saveBook(isbn, principalDetails);
            resultmsg = "<script>alert('보관 완료!');location.href='/bookshelf'</script>";
        }

        return resultmsg;
    }

    // 비로그인 상태에서 내 서재 보관 클릭 시 로그인 메세지 출력
    @GetMapping("message")
    public String message(Model model){

        model.addAttribute("message", "로그인 후 이용가능합니다.");
        model.addAttribute("url", "/oauth2/authorization/naver");

        return "message";
    }

    // 한줄평 삭제 버튼 클릭
    @GetMapping("deleteR/{id}")
    @ResponseBody
    public String deleteReview(@PathVariable Long id) {

        String resultMsg = "";

        reviewService.deleteReview(id);

        resultMsg = "<script>alert('한줄평이 삭제되었습니다.');history.go(-1)</script>";

        return resultMsg;
    }

    // 나만의 구절 삭제 버튼 클릭
    @GetMapping("deleteV/{id}")
    @ResponseBody
    public String deleterVerse(@PathVariable Long id) {

        String resultMsg = "";

        verseService.deleteVerse(id);

        resultMsg = "<script>alert('나만의 구절이 삭제되었습니다.');history.go(-1)</script>";

        return resultMsg;

    }
}

