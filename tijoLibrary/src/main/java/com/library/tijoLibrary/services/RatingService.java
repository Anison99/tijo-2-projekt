package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.models.Rating;
import com.library.tijoLibrary.models.User;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
@Service
public class RatingService {
    public Rating getUserRatingForBook(User user, Book book) {
        return null;
    }

    public void saveRating(Rating rating) {
        //todo: implementacja metody
=======
import java.util.List;

@Service
public class RatingService {

    // Dodanie oceny
    public void addRating(Long userId, Long bookId, int rating) {

    }

    // Usunięcie oceny
    public void deleteRating(Long ratingId) {

    }

    // Zmiana oceny
    public void updateRating(Long ratingId, int newRating) {

    }

    // Pobranie listy ocen dla książki
    public List<Rating> getRatingsForBook(Long bookId) {
        return null;
    }

    // Dodanie recenzji
    public void addReview(Long userId, Long bookId, String review) {
    }

    // Zmiana recenzji
    public void updateReview(Long ratingId, String newReview) {

    }

    // Usunięcie recenzji
    public void deleteReview(Long ratingId) {

    }

    // Pobranie recenzji dla książki
    public List<Rating> getReviewsForBook(Long bookId) {
        return null;
>>>>>>> origin/develop
    }
}
