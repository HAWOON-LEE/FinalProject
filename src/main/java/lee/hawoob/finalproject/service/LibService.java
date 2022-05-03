package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.LibId;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.LibRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LibService {

    @Autowired
    private LibRepository libRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Lib> findAllByNickname(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Lib> isbns = libRepository.findAll();
        User user = userRepository.findByNickname(principalDetails.getUsername());

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
        Long sum = libRepository.countByBookIsbn(isbn);

        return sum;
    }

    public void saveBook(String isbn, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        LibId libId = new LibId();

        libId.setIsbn(isbn);
        libId.setNickname(String.valueOf(principalDetails));

        Lib lib = new Lib();
        lib.setLibId(libId);

        libRepository.save(lib);
    }
}
