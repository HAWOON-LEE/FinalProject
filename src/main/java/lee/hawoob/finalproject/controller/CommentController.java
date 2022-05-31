package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Comment;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping("/createComment")
    public String createComment(@ModelAttribute Comment comment, Long BoardIndex, PrincipalDetails custom){
        User user = custom.getUser();

        return service.createComment(comment, user, BoardIndex);
    }

    @PostMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id){
        return service.deleteComment(id);
    }
}
