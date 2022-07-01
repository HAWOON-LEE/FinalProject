package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.*;
import lee.hawoob.finalproject.form.VerseForm;
import lee.hawoob.finalproject.repository.BookRepository;
import lee.hawoob.finalproject.repository.LibRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lee.hawoob.finalproject.repository.VerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VerseService {

    @Autowired
    private VerseRepository verseRepository;
    @Autowired
    private LibRepository libRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    // 현재 이용자의 내 서재에 담긴 도서 데이터를 불러온다
    public List<Lib> findByNickname(@AuthenticationPrincipal PrincipalDetails principalDetails){

        List<Lib> libList = libRepository.findAll();
        User user = userRepository.findByNickname(principalDetails.getUsername());

        List<Lib> myBookList = new ArrayList<>();

        for(int i=0; i < libList.size(); i++){
            if(libList.get(i).getUser().getNickname() == user.getNickname()){
                myBookList.add(libList.get(i));
            }
        }
        return myBookList;
    }

    // 도서별 나만의구절 정보를 불러오기
    public List<Verse> findAllVerseByIsbn(String isbn) {

        Book book = bookRepository.findByIsbn(isbn);

        List<Verse> verses = verseRepository.findAllByBook(book);

        return verses;
    }

    // Controller로부터 form에 담긴 데이터를 받아 Entity로 변환 후 저장
    public void saveVerse(@ModelAttribute VerseForm verseForm,
                          @AuthenticationPrincipal PrincipalDetails principalDetails){

        Verse verse = new Verse();

        User user = userRepository.findById(principalDetails.getUser().getUser_id()).get();
        Book book = bookRepository.findByIsbn(verseForm.getBook().getIsbn());

        verse.setBook(book);
        verse.setUser(user);
        verse.setVerse(verseForm.getVerse());
        verse.setSub(verseForm.getSub());
        verse.setPage(verseForm.getPage());

        verseRepository.save(verse);
    }

    // 나만의 구절 삭제
    public void deleteVerse(Long index){
        verseRepository.deleteById(index);
    }

}
