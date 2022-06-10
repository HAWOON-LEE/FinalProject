package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.CommentDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.Comment;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.form.CreateCommentForm;
import lee.hawoob.finalproject.repository.BoardRepository;
import lee.hawoob.finalproject.repository.CommentRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository repository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public List<CommentDto> getCommentList(Long boardIndex){
//        Board board = new Board();
//        board.setBoardIndex(boardIndex);
//        List<CommentDto> dto = repository.findByBoard(boardRepository.findById(boardIndex).get());
//                repository.findAll().stream().map(c -> new CommentDto(c)).collect(Collectors.toList());
        List<CommentDto> dto = repository.findCommentByBoard(boardRepository.findById(boardIndex).get()).stream().map(c -> new CommentDto(c)).collect(Collectors.toList());

        return dto;
    }

    public void createComment(CreateCommentForm form, @AuthenticationPrincipal PrincipalDetails custom){
        User user = userRepository.findById(custom.getUser().getUser_id()).get();
        Comment comment = new Comment();
        System.out.println(form.getBoardIndex());
//        System.out.println(form.getBoard().getBoardIndex());

        comment.setBoard(boardRepository.getById(form.getBoardIndex()));
        comment.setComment(form.getComment());
        comment.setUser(user);

        repository.save(comment);
    }

    public String deleteComment(Long id){
        repository.deleteById(id);
//        repository.delete(comment);

        return "home";
    }

}
