package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.LibId;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.BookRepository;
import lee.hawoob.finalproject.repository.LibRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LibService {

    private final LibRepository repository;

    private final BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Lib> findAllByNickname(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Lib> isbns = repository.findAll();
        User user = userRepository.findByNickname(principalDetails.getUser().getNickname());

        List<Lib> libs = new ArrayList<>();
        for(int i=0; i < isbns.size(); i++) {
            if(isbns.get(i).getUser().getNickname() == user.getNickname()) {
                libs.add(isbns.get(i));
            }
        }
        System.out.println(libs);
        return libs;
    }

    public Long countByBookIsbn(String isbn) {
        Long sum = repository.countByBookIsbn(isbn);

        return sum;
    }

//    public void saveBook(String isbn, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        Lib lib = new Lib();
//        Book book = bookRepository.findBookByIsbn(isbn).get();
//
//
//        lib.setBook(book);
//        lib.setUser(principalDetails.getUser());
//
//        repository.save(lib);
//    }
public void saveBook(Lib lib) {
    repository.save(lib);
}

}
