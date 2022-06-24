package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.CommentDto;
import lee.hawoob.finalproject.entity.Comment;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.form.CreateCommentForm;
import lee.hawoob.finalproject.repository.BoardRepository;
import lee.hawoob.finalproject.repository.CommentRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository repository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public List<CommentDto> getCommentList(Long boardIndex){
        List<CommentDto> dto = repository.findCommentByBoard(boardRepository.findById(boardIndex).get()).stream().map(c -> new CommentDto(c)).collect(Collectors.toList());

        return dto;
    }

    public void createComment(CreateCommentForm form, @AuthenticationPrincipal PrincipalDetails custom){
        User user = userRepository.findById(custom.getUser().getUser_id()).get();
        Comment comment = new Comment();

        comment.setBoard(boardRepository.getById(form.getBoardIndex()));
        comment.setComment(form.getComment());
        comment.setUser(user);

        repository.save(comment);
    }

    public void deleteComment(Long id){
        repository.deleteById(id);
    }

    public Long findBoardIndexByCommentId(Long id){
        return repository.findById(id).get().getBoard().getBoardIndex();
    }

}
