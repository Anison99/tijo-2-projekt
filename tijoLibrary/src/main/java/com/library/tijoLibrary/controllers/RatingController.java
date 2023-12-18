package com.library.tijoLibrary.controllers;

import com.library.tijoLibrary.models.Rating;
import com.library.tijoLibrary.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/book-rating")
    public String bookRating() {
        return "book-rating";
    }

    @PostMapping("/api/ratings/add")
    public ResponseEntity<?> addRating(@RequestBody RatingRequest ratingRequest) {
        try {
            // Dodanie oceny
            Rating rating = ratingService.addRatingandReview(
                    ratingRequest.getUserId(),
                    ratingRequest.getBookId(),
                    ratingRequest.getRating(),
                    ratingRequest.getReview()
            );

            // Dodanie recenzji, jeśli istnieje
          /*  if (ratingRequest.getReview() != null && !ratingRequest.getReview().trim().isEmpty()) {
                ratingService.addReview(
                        ratingRequest.getUserId(),
                        ratingRequest.getBookId(),
                        ratingRequest.getReview()
                );
            }

           */
            return ResponseEntity.ok(rating);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/api/ratings/book/{bookId}")
    public ResponseEntity<List<Rating>> getRatingsForBook(@PathVariable Long bookId) {
        try {
            List<Rating> ratings = ratingService.getRatingsForBook(bookId);
            return ResponseEntity.ok(ratings);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/api/ratings/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable Long userId) {
        try {
            List<Rating> ratings = ratingService.getReviewsMadeByUser(userId);
            return ResponseEntity.ok(ratings);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/api/ratings/{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable Long ratingId) {
        try {
            ratingService.deleteRating(ratingId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/api/ratings/{ratingId}")
    public ResponseEntity<?> updateRating(@PathVariable Long ratingId, @RequestBody RatingRequest ratingRequest) {
        try {
            ratingService.updateRating(ratingId, ratingRequest.getRating());
            ratingService.updateReview(ratingId, ratingRequest.getReview());
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

class RatingRequest {
    private Long userId;
    private Long bookId;
    private int rating;
    private String review;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
