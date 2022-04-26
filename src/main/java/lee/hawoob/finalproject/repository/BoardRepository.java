package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Board;
//import MBTI.dto.BoardCreateDto;
import lee.hawoob.finalproject.dto.CreateBoardDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
//import MBTI.dto.SearchInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


    @Query(value = "select b from Board b where b.title like %:keyword% or b.content like %:keyword% or b.user.nickname like %:keyword% ")
    List<Board> findByBoardTitleAndPostContentContaining(String keyword);

    @Query(value = "delete from board b where b.boardIndex = :boardIndex", nativeQuery = true)
    List<Board> deleteBoardById(Long boardIndex);



}