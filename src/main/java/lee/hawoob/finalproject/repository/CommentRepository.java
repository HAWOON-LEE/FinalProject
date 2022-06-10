package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.dto.CommentDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<CommentDto> findByBoard(Board board);

    List<Comment> findCommentByBoard(Board board);
}
