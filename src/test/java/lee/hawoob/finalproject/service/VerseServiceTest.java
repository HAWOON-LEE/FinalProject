package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.entity.Verse;
import lee.hawoob.finalproject.form.VerseForm;
import lee.hawoob.finalproject.repository.VerseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import static org.junit.jupiter.api.Assertions.*;

class VerseServiceTest {

    VerseRepository repository;
    @Test
    void saveVerse(){
        Verse verse = new Verse();
        Book book = new Book();
        User user = new User();

        verse.setVerse("ㅇㅅ");
        verse.setBook(book);
        verse.setSub("");
        verse.setPage(54);
        verse.setUser(user);

        repository.save(verse);

        System.out.println(verse.getVerse());
    }
}