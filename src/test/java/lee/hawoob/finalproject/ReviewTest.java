package lee.hawoob.finalproject;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Review;
import lee.hawoob.finalproject.form.ReviewForm;
import lee.hawoob.finalproject.repository.BookRepository;
import lee.hawoob.finalproject.repository.ReviewRepository;
import lee.hawoob.finalproject.service.BookService;
import lee.hawoob.finalproject.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class ReviewTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Test
    public void createReview() {

        ReviewForm reviewForm = new ReviewForm();

        reviewForm.setReview("하이");
        reviewForm.setIsbn("1130605213 9791130605210");
        reviewForm.setRating(5);

        System.out.println(reviewForm);

        Book book = bookService.findByIsbn(reviewForm.getIsbn());

        Review review = new Review();

        review.setReview(reviewForm.getReview());
        review.setBook(book);
        review.setRating(reviewForm.getRating());

        System.out.println(review);
    }

    @Test
    public void findAllReview() {

       Book book = bookService.findByIsbn("1190492970 9791190492973");

       List<Review> reviewList = reviewRepository.findAllByBook(book);

        System.out.println(reviewList);
    }

    @Test
    public void convertToStar() {

        String isbn = "1190492970 9791190492973";
        String star = "";

        List<Review> reviews = reviewService.findAllReviewByIsbn(isbn);

        List<String> stars = new ArrayList<>();

        for(int i=0; i<reviews.size(); i++) {
            Integer rating = reviews.get(i).getRating();

        }


        System.out.println(star);
    }

//    @Test
//    public void calculateRating() {
//
//        String isbn = "1190492970 9791190492973";
//
//        Book book = bookRepository.findByIsbn(isbn);
//
//        List<Review> reviews = reviewRepository.findAllByBook(book);
//
//        Integer addRating = 0;
//        Integer avgRating = 0;
//
//        for(int i=0; i<reviews.size(); i++) {
//
//            Integer rating = reviews.get(i).getRating();
//
//            addRating += rating;
//        }
//        avgRating = Math.round(addRating / reviews.size());
//
//        System.out.println(avgRating+"점");
//    }

}
