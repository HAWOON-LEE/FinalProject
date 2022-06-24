package lee.hawoob.finalproject;

import lee.hawoob.finalproject.entity.Verse;
import lee.hawoob.finalproject.repository.VerseRepository;
import lee.hawoob.finalproject.service.VerseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class VerseTest {

    @Autowired
    private VerseRepository verseRepository;
    @Autowired
    private VerseService verseService;

    @Test
    public void getVerseList() {

        String isbn = "1190492970 9791190492973";

        List<Verse> verseList = verseService.findAllVerseByIsbn(isbn);

        System.out.println(verseList);
    }
}
