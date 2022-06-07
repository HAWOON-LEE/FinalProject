package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Comment;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.form.CreateCommentForm;
import lee.hawoob.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping("/createComment")
    public void createComment(@ModelAttribute CreateCommentForm dto, @AuthenticationPrincipal PrincipalDetails custom, Model model){
        model.addAttribute("dto", dto);

        service.createComment(dto, custom);
//        return "redirect:/detail/"
    }

    @PostMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id){
        return service.deleteComment(id);
    }
}
