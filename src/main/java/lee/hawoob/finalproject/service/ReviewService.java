package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.dto.ReviewDto;
import lee.hawoob.finalproject.entity.*;
import lee.hawoob.finalproject.form.ReviewForm;
import lee.hawoob.finalproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository repository;

    private final LibRepository libRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;


    public List<LibDto> findByNickname(@AuthenticationPrincipal PrincipalDetails custom){
        List<Lib> libList = libRepository.findAll();
        User user = userRepository.findByNickname(custom.getUser().getNickname());

        List<LibDto> libs = new ArrayList<>();
        for(int i=0; i < libList.size(); i++){
            if(libList.get(i).getUser().getNickname() == user.getNickname()){
                libs.add(libList.get(i));
            }
        }
        return libs;
    }

    public void saveReview(ReviewForm form, @AuthenticationPrincipal PrincipalDetails custom){
        Review review = new Review();
        User user = userRepository.findById(custom.getUser().getUser_id()).get();
        Book book = bookRepository.findBookByIsbn(form.getBook().getIsbn()).get();

        review.setBook(book);
        review.setUser(user);
        review.setReview(form.getReview());
        review.setSub(form.getSub());
        review.setRating(form.getRating());

        repository.save(review);
    }
    public void save(Review review){
        repository.save(review);
    }


    public void deleteReview(Long index){
        repository.deleteById(index);
    }

    public void updateReview(ReviewDto dto){
        Optional<Review> opReview = repository.findById(dto.getIndex());

        Review review = opReview.get();

        review.setReview(dto.getReview());
        review.setIndex(dto.getIndex());
        review.setRating(dto.getRating());
        review.setSub(dto.getSub());

        repository.save(review);
    }
}
