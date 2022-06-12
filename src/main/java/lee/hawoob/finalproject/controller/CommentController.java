package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.form.CreateCommentForm;
import lee.hawoob.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping("/createComment")
    public String createComment(@ModelAttribute CreateCommentForm dto, @AuthenticationPrincipal PrincipalDetails custom, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("dto", dto);
        redirectAttributes.addAttribute("boardIndex", dto.getBoardIndex());

        service.createComment(dto, custom);
        return "redirect:/board/detail/{boardIndex}";
    }

    @PostMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id, RedirectAttributes redirectAttributes){
        service.deleteComment(id);
        Long boardIndex = service.findBoardIndexByCommentId(id);

        redirectAttributes.addAttribute("boardIndex", boardIndex);
        return "redirect:/board/detail/{boardIndex}";
    }
}
