package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.LibDto;
import lee.hawoob.finalproject.dto.VerseDto;
import lee.hawoob.finalproject.entity.*;
import lee.hawoob.finalproject.form.ReviewForm;
import lee.hawoob.finalproject.form.VerseForm;
import lee.hawoob.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
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

    public void saveReview(ReviewForm form, @AuthenticationPrincipal PrincipalDetails custom){
        Review review = new Review();
        User user = userRepository.findById(custom.getUser().getUser_id()).get();
        Book book = bookRepository.findById(form.getIsbn()).get();


        review.setReview(form.getReview());
        review.setBook(book);
        review.setRating(form.getRating());
        review.setUser(user);

        reviewRepository.save(review);
    }

    public void save(Review review){
        reviewRepository.save(review);
    }

    public void deleteReview(Long index){
        reviewRepository.deleteById(index);
    }

}

