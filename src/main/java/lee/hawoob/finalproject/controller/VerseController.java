package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.dto.VerseDto;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.entity.Verse;
import lee.hawoob.finalproject.form.VerseForm;
import lee.hawoob.finalproject.service.VerseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("verse")
@AllArgsConstructor
public class VerseController {

    @Autowired
    VerseService service;
    
    @GetMapping("list")
    public ModelAndView verse(VerseDto dto, @AuthenticationPrincipal PrincipalDetails custom, Model model){
        model.addAttribute("dto", dto);
        List<LibDto> libs = service.findByNickname(custom);

        ModelAndView mav = new ModelAndView();
        mav.addObject("libs", libs);
        mav.setViewName("passage");

        return mav;
    }

    @PostMapping("enroll")
    public String addVerse(@ModelAttribute VerseForm form, @AuthenticationPrincipal PrincipalDetails custom, Model model){
        model.addAttribute("dto", form);
        model.addAttribute("libs", form);

        Verse verse = new Verse();
        verse.setVerse(form.getVerse());
        verse.setPage(form.getPage());
        verse.setSub(form.getSub());
        verse.setUser(custom.getUser());
        verse.setBook(form.getBook());

        service.save(verse);

        return "redirect:/verse/list";
    }


}
