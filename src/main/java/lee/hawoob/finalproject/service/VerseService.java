package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.dto.VerseDto;
import lee.hawoob.finalproject.entity.*;
import lee.hawoob.finalproject.repository.LibRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lee.hawoob.finalproject.repository.VerseRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Transactional
public class VerseService {

    private final VerseRepository repository;

    private final LibRepository libRepository;

    private final UserRepository userRepository;

    public List<LibDto> findAll(@AuthenticationPrincipal PrincipalDetails custom){
        List<LibDto> libDtoList = libRepository.findAll().stream().map(L -> new LibDto(L)).collect(Collectors.toList());
//        LibDto dto = new LibDto();
        User user = userRepository.findById(custom.getUser().getUser_id()).get();

        List<LibDto> libs = new ArrayList<>();
        for(int i=0; i >= libDtoList.size(); i++){
            if(libDtoList.get(i).getUser().getNickname() == user.getNickname()){
                libs.add(libDtoList.get(i));
            }
        }
        System.out.println(libs);
        return libs;
    }

    public void saveVerse(VerseDto verseDto,LibDto libDto, @AuthenticationPrincipal PrincipalDetails custom){
        Verse verse = new Verse();
        User user = userRepository.findById(custom.getUser().getUser_id()).get();

        verse.setVerse(verseDto.getVerse());
        verse.setBook(libDto.getBook());
        verse.setSub(verseDto.getSub());
        verse.setPage(verseDto.getPage());
        verse.setUser(user);

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
