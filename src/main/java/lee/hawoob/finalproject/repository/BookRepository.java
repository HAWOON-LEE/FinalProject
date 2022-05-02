package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.dto.BookDto;
import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Mbti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByMbti_Mbti(@Param(value = "mbti") String mbti);

    List<Book> findByIsbn(String isbn);

}
