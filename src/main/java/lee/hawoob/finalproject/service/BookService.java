package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.dto.BookDto;
import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllByMbti(String mbti) {
        List<Book> books = bookRepository.findAllByMbti_Mbti(mbti);

        return books;
    }


//    public Book selectTop1() {
//        List<Book> books = bookRepository.selectTop3();
//        Book book1 = new Book();
//        book1 = books.get(0);
//        return book1;
//    }
//
//    public Book selectTop2() {
//        List<Book> books = bookRepository.selectTop3();
//        Book book2 = new Book();
//        book2 = books.get(1);
//        return book2;
//    }
//
//    public Book selectTop3() {
//        List<Book> books = bookRepository.selectTop3();
//        Book book3 = new Book();
//        book3 = books.get(2);
//        return book3;
//    }
//
//    public String top1Img() {
//        Book book1 = selectTop1();
//        String top1Img = book1.getImageUrl();
////        BookDto bookDto = new BookDto(book1);
////        String top1Img = bookDto.getImageUrl();
//        return top1Img;
//    }
//
}
