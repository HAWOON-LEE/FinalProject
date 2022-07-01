package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByMbti_Mbti(String mbti);

    Book findByIsbn(String isbn);

    // 검색 키워드가 포함하는 작가, 도서명을 가진 도서 데이터를 가져오기
    @Query(value = "select b from Book b where b.title like %:keyword% or b.author like %:keyword%")
    List<Book> findByKeywordContaining(String keyword);

}
