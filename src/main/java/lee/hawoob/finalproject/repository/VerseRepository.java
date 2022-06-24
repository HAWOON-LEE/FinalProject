package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Verse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerseRepository extends JpaRepository<Verse, Long> {

    // 해당 도서를 기준으로 모든 나만의구절 불러오기
    List<Verse> findAllByBook(Book book);
}
