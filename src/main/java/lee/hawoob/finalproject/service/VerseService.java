package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.dto.VerseDto;
import lee.hawoob.finalproject.entity.*;
import lee.hawoob.finalproject.form.VerseForm;
import lee.hawoob.finalproject.repository.BookRepository;
import lee.hawoob.finalproject.repository.LibRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lee.hawoob.finalproject.repository.VerseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VerseService {

    @Autowired
    VerseRepository repository;
    @Autowired
    LibRepository libRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    public List<LibDto> findByNickname(@AuthenticationPrincipal PrincipalDetails custom){
        List<Lib> libList = libRepository.findAll();
        User user = userRepository.findByNickname(custom.getUsername());

        List<LibDto> libs = new ArrayList<>();
        for(int i=0; i < libList.size(); i++){
            if(libList.get(i).getUser().getNickname() == user.getNickname()){
                libs.add(libList.get(i));
            }
        }
        return libs;
    }

    public void saveVerse(VerseForm form, @AuthenticationPrincipal PrincipalDetails custom){
        Verse verse = new Verse();
        User user = userRepository.findById(custom.getUser().getUser_id()).get();
        Book book = bookRepository.findById(form.getIsbn()).get();


        verse.setVerse(form.getVerse());
        verse.setBook(book);
        verse.setSub(form.getSub());
        verse.setPage(form.getPage());
        verse.setUser(user);

        repository.save(verse);
    }
    public void save(Verse verse){
        repository.save(verse);
    }


    public void deleteVerse(Long index){
        repository.deleteById(index);
    }

    public void updateVerse(VerseDto dto){
        Optional<Verse> opVerse = repository.findById(dto.getIndex());

        Verse verse = opVerse.get();

        verse.setVerse(dto.getVerse());
        verse.setIndex(dto.getIndex());
        verse.setPage(dto.getPage());
        verse.setSub(dto.getSub());

        repository.save(verse);
    }
}
