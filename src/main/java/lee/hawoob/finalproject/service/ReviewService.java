package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.*;
import lee.hawoob.finalproject.form.ReviewForm;
import lee.hawoob.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private LibRepository libRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    // 현재 이용자의 내 서재에 담긴 도서 데이터를 불러온다
    public List<Lib> findByNickname(@AuthenticationPrincipal PrincipalDetails principalDetails){

        List<Lib> libList = libRepository.findAll();
        User user = userRepository.findByNickname(principalDetails.getUser().getNickname());

        List<Lib> myBookList = new ArrayList<>();

        for(int i=0; i < libList.size(); i++){
            if(libList.get(i).getUser().getNickname() == user.getNickname()){
                myBookList.add(libList.get(i));
            }
        }
        return myBookList;
    }

    // 도서별 리뷰 정보를 불러오기
    public List<Review> findAllReviewByIsbn(String isbn) {

        Book book = bookRepository.findByIsbn(isbn);

        List<Review> reviews = reviewRepository.findAllByBook(book);

        return reviews;
    }

    // 각 도서별 리뷰들의 rating 값의 평균을 계산
    public Integer calculateRating(String isbn) {

        List<Review> reviews = findAllReviewByIsbn(isbn);

        Integer addRating = 0;
        Integer avgRating = 0;

        for(int i=0; i<reviews.size(); i++) {

            Integer rating = reviews.get(i).getRating();

            addRating += rating;

        }

        if(reviews.size() == 0) {
            avgRating = 0;
        } else {
            avgRating = Math.round(addRating/reviews.size());
        }

        return avgRating;
    }

    //  각 리뷰들의 index값과, rating값을 별 문자로 치환한 값을 Map에 담는다
    public Map<Long, String> makeStarMap(String isbn) {

        List<Review> reviewList = findAllReviewByIsbn(isbn);
        Map<Long, String> starMap = new HashMap<>();

        for(int i=0; i<reviewList.size(); i++) {

            Long index = reviewList.get(i).getIndex();

            Integer rating = reviewList.get(i).getRating();

            String star = convertToStar(rating);

            starMap.put(index, star);
        }

        return starMap;
    }

    // rating 점수를 별 문자로 치환
    public String convertToStar(Integer rating) {

        String star = "";

            switch (rating)
            {
                case 0:
                    star = "☆☆☆☆☆";
                    break;
                case 1:
                    star = "★☆☆☆☆";
                    break;
                case 2:
                    star = "★★☆☆☆";
                    break;
                case 3:
                    star = "★★★☆☆";
                    break;
                case 4:
                    star = "★★★★☆";
                    break;
                case 5:
                    star = "★★★★★";
                    break;
            }
            return star;
    }

    // Controller로부터 form에 담긴 데이터를 받아 Entity로 변환 후 저장
    public void saveReview(@ModelAttribute ReviewForm reviewForm,
                           @AuthenticationPrincipal PrincipalDetails principalDetails){

        Review review = new Review();

        User user = userRepository.findById(principalDetails.getUser().getUser_id()).get();
        Book book = bookRepository.findByIsbn(reviewForm.getBook().getIsbn());

        review.setBook(book);
        review.setUser(user);
        review.setReview(reviewForm.getReview());
        review.setRating(reviewForm.getRating());

        reviewRepository.save(review);
    }

    // 한줄평 삭제
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

}
