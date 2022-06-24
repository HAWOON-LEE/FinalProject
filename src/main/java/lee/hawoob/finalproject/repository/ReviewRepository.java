package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 해당 도서를 기준으로 모든 리뷰 불러오기
    List<Review> findAllByBook(Book book);
}
