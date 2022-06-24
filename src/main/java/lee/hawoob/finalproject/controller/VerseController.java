package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.form.VerseForm;
import lee.hawoob.finalproject.service.VerseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("verse")
public class VerseController {

    @Autowired
    private VerseService verseService;

    // 상단 태그를 통해 접근 시, 로그인된 사용자의 서재에 담긴 도서 정보와, 사용자의 입력정보를 받아올 form객체를 화면에 전달
    @GetMapping("")
    public String gotoWriteVerse(VerseForm verseForm, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        List<Lib> libs = verseService.findByNickname(principalDetails);

        model.addAttribute("verseForm", verseForm);
        model.addAttribute("libs", libs);

        return "verse";
    }

    // 나만의 구절 등록 시 입력 데이터 검증 후, 확인 메시지 출력 및 등록
    @PostMapping("enroll")
    public String addVerse(@ModelAttribute @Validated VerseForm verseForm,
                           BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        if(bindingResult.hasErrors()) {

            List<Lib> libs = verseService.findByNickname(principalDetails);

            model.addAttribute("verseForm", verseForm);
            model.addAttribute("libs", libs);

            return "verse";

        }

        verseService.saveVerse(verseForm, principalDetails);
        System.out.println("저장완료");

        List<Lib> libs = verseService.findByNickname(principalDetails);

        model.addAttribute("message", "나만의 구절이 등록되었습니다.");
        model.addAttribute("url", "/verse");
        model.addAttribute("verseForm", verseForm);
        model.addAttribute("libs", libs);

        return "message";
    }

}
