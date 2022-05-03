package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.dto.BookDto;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.repository.BookRepository;
import lee.hawoob.finalproject.repository.LibRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibRepository libRepository;

    // mbti로 도서목록 찾기기
   public List<Book> findAllByMbti(String mbti) {
        List<Book> books = bookRepository.findAllByMbti_Mbti(mbti);

        return books;
    }

    // isbn넘버로 데이터 찾기
    public List<Book> findByIsbn(String isbn) {
        List<Book> books = bookRepository.findByIsbn(isbn);

        return books;
    }

    public int cntByIsbn(String isbn){
        Book book = new Book();
        book.setIsbn("1130605213 9791130605210");
        List<Lib> lib = libRepository.findAll();
        List<LibDto> bookCnt = new ArrayList<>();

        for(int i = 0; i < lib.size(); i++){
            if(lib.get(i).getBook().getIsbn().equals(book.getIsbn())){
                bookCnt.add(lib.get(i));
            }
        }

        System.out.println(bookCnt.size());
        return bookCnt.size();
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
