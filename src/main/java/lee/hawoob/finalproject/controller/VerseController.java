package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.dto.VerseDto;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.service.VerseService;
import lombok.AllArgsConstructor;
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

    private final VerseService service;

//    @GetMapping("list")
    public ModelAndView addVerse(@ModelAttribute("libs") VerseDto dto, Model model, ModelAndView mav,@RequestParam(value = "custom", required = false) @AuthenticationPrincipal PrincipalDetails custom){
        mav.setViewName("passage");
        mav.addObject("dto", dto);

        List<LibDto> libs = service.findAll(custom);

        model.addAttribute("libs", libs);
        return mav;
    }
    @GetMapping("list")
    public ModelAndView verse(VerseDto dto, @AuthenticationPrincipal PrincipalDetails custom, Model model){
//        model.addAttribute("dto", dto);
        List<LibDto> libs = service.findAll(custom);
        ModelAndView mav = new ModelAndView();
        mav.addObject("libs", libs);
        mav.setViewName("passage");
        return mav;
    }

    @PostMapping("enroll")
    public String addVerse(@ModelAttribute VerseDto verseDto, LibDto libDto, @AuthenticationPrincipal PrincipalDetails custom, Model model){
        model.addAttribute("dto", verseDto);
        model.addAttribute("libs", libDto);
        service.saveVerse(verseDto, libDto,custom);


        return "redirect:/verse";
    }




}
