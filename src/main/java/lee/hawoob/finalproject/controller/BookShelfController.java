package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookshelf")
public class BookShelfController {

    @Autowired
    private LibService libService;

    // 내 서재 목록 불러오기
    @GetMapping
    public String showMyList(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<Lib> books = libService.findAllByNickname(principalDetails);

        model.addAttribute("books", books);

        return "bookshelf";
    }

    // 내 서재에서 삭제
    @PostMapping("/delete")
    @ResponseBody // Spring에서 비동기처리를 하기 위한 어노테이션
    public String deleteMyBook(@RequestParam("isbn") String isbn,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String resultMsg = "";

        libService.deleteBook(isbn, principalDetails);

        resultMsg = "<script>alert('서재에서 삭제되었습니다.');location.href='/bookshelf'</script>";

        return resultMsg;
    }
}
