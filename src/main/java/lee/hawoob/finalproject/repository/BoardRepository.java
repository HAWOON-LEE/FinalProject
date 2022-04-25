package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Board;
//import MBTI.dto.BoardCreateDto;
import lee.hawoob.finalproject.dto.CreateBoardDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
//import MBTI.dto.SearchInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select b from Board b where b.title like %:keyword% or b.content like %:keyword% or b.user.nickname like %:keyword% ")
    List<Board> findByBoardTitleAndPostContentContaining(String keyword);



    @Query(value = "delete b from board b where b.user.id = :id and b.boardIndex = :boardIndex", nativeQuery = true)
    Board deleteBoardById(@Param("id") Long boardIndex, @Param("id") Long id);

//     Optional<Board> findById(Long postIndex);
//    void save(CreateBoardDto dto) throws Exception;

//    void updateBoard();

//    void delete(Long boardIndex);

}