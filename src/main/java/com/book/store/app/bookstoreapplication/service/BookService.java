package com.book.store.app.bookstoreapplication.service;

import com.book.store.app.bookstoreapplication.model.Book;
import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.model.Review;
import com.book.store.app.bookstoreapplication.repository.BookRepository;
import com.book.store.app.bookstoreapplication.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private SignupService signupService;
    @Autowired
    private ReviewRepository reviewRepository;
    public Page<Book> findBooks(int pageNum){
        int pageSize=5;
        Pageable pageable= PageRequest.of(pageNum-1,pageSize);
        Page<Book>bookPage=bookRepository.findAll(pageable);
        return bookPage;
    }
    public List<Book> searchBook(String parameter){
        List<Book> books=bookRepository.findAll();
        return books.stream().filter(book -> book.contains(parameter)).toList();
    }
    public int postReview(String rating,String reviewText,int bookId,String userName){
        Book book=bookRepository.getReferenceById(bookId);
        NewUser loggedInUser=signupService.getUserByFirstName(userName);
        Review review=new Review();
        review.setBook(book);
        review.setReviewText(reviewText);
        review.setRating(Integer.parseInt(rating));
        review.setUser(loggedInUser);
        return reviewRepository.save(review).getReviewId();
    }
    public int getGrantReviews(List<Review>lst){
        int sum=0;
        if(lst.size()==0)
            return sum;
        for(Review r:lst)
            sum+=r.getRating();
        return sum/lst.size();
    }

}
