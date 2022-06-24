package lee.hawoob.finalproject;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.repository.BookRepository;
import lee.hawoob.finalproject.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class BookTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void searchBooks() {

        String keyword = "인생";

        List<Book> books = bookRepository.findByKeywordContaining(keyword);

        List<String> bookTitle = new ArrayList<>();

        for(int i=0; i<books.size(); i++) {

            String title = books.get(i).getTitle();

            bookTitle.add(title);
        }

        System.out.println(bookTitle);
    }
}
