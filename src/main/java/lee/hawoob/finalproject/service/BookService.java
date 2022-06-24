package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // mbti로 도서목록 찾기
   public List<Book> findAllByMbti(String mbti) {

       List<Book> books = bookRepository.findAllByMbti_Mbti(mbti);

       return books;
    }

    // isbn넘버로 도서 찾기
    public Book findByIsbn(String isbn) {

        Book books = bookRepository.findByIsbn(isbn);

        return books;
    }

    // 검색 키워드로 도서목록 찾기
    public List<Book> findByKeywordContaining(String keyword) {

       List<Book> books = bookRepository.findByKeywordContaining(keyword);

       return books;
    }
}
