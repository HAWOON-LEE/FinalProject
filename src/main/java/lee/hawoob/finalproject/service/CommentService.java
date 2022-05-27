package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.dto.CommentDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.Comment;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.BoardRepository;
import lee.hawoob.finalproject.repository.CommentRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository repository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public List<CommentDto> getCommentList(Long boardIndex){
//        Board board = new Board();
//        boardIndex = board.getBoardIndex();
        List<CommentDto> dto = repository.findByBoardindex(boardIndex);

        return dto;
    }

    public String createComment(Comment comment, User user, Long boardId){

        User findUser = userRepository.findById(user.getUser_id()).get();
        Optional<Board> findBoard = boardRepository.findById(boardId);

        comment.setBoard(findBoard.get());
        comment.setUser(findUser);
        repository.save(comment);

        return "home";
    }

    public String deleteComment(Comment comment){

        repository.delete(comment);

        return "home";
    }

}
