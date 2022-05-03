package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.boardIndex = :boardIndex")
    int updateView(Long boardIndex);

    @Query(value = "select b from Board b where b.title like %:keyword% or b.content like %:keyword% or b.user.nickname like %:keyword% ")
    Page<Board> findByBoardTitleAndPostContentContaining(String keyword, Pageable pageable);

}