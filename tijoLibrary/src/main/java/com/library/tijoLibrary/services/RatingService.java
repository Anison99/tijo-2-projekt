package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Rating;
import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.BookRepository;
import com.library.tijoLibrary.repositories.RatingRepository;
import com.library.tijoLibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public Rating addRating(Long userId, Long bookId, int ratingValue) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setBook(book);
        rating.setRating(ratingValue);
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    public void updateRating(Long ratingId, int newRating) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Rating with ID " + ratingId + " not found"));
        rating.setRating(newRating);
        ratingRepository.save(rating);
    }

    public List<Rating> getRatingsForBook(Long bookId) {
        return ratingRepository.findByBookId(bookId);
    }

    public Rating addReview(Long userId, Long bookId, String review) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));

        Rating newRating = new Rating();
        newRating.setUser(user);
        newRating.setBook(book);
        newRating.setReview(review);
        return ratingRepository.save(newRating);
    }

    public void updateReview(Long ratingId, String newReview) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Rating with ID " + ratingId + " not found"));
        rating.setReview(newReview); // Zakładając, że pole nazywa się 'recension'
        ratingRepository.save(rating);
    }
    public void deleteReview(Long ratingId) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Rating with ID " + ratingId + " not found"));
        rating.setReview(null); // Usunięcie recenzji poprzez ustawienie na null
        ratingRepository.save(rating);
    }
    public Rating addRatingandReview(Long userId, Long bookId, int ratingValue, String review) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setBook(book);
        rating.setRating(ratingValue);
        rating.setReview(review);
        return ratingRepository.save(rating);
    }

    public List<Rating> getReviewsForBook(Long bookId) {
        return ratingRepository.findByBookId(bookId);
    }

    public List<Rating> getReviewsMadeByUser(Long userId) {
        return ratingRepository.findByUserId(userId);
    }
}