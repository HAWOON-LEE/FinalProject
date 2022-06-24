package lee.hawoob.finalproject.repository;
import lee.hawoob.finalproject.dto.CommentDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<CommentDto> findByBoard(Board board);

    List<Comment> findCommentByBoard(Board board);
}
