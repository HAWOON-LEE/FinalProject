package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
