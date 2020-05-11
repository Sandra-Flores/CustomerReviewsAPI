package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.*;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.MongoCommentRepository;
import com.udacity.course3.reviews.repository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionException;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    //Wire needed JPA repositories
    @Autowired private CommentRepository commentRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private MongoReviewRepository mongoReviewRepository;
    @Autowired private MongoCommentRepository mongoCommentRepository;

    CommentsController(CommentRepository commentRepository, ReviewRepository reviewRepository,
                       MongoReviewRepository mongoReviewRepository, MongoCommentRepository mongoCommentRepository) {
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.mongoCommentRepository = mongoCommentRepository;
        this.mongoReviewRepository = mongoReviewRepository;
    }

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MongoComment> createCommentForReview(@Valid @PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {

        Optional<Review> optReview = reviewRepository.findById(reviewId);
        Optional<MongoReview> optMongoReview = mongoReviewRepository.findById(reviewId);
        MongoComment mongoComment;

        if(optMongoReview.isPresent()){
            comment.setReview(optReview.get());
            comment = commentRepository.save(comment);

            mongoComment = new MongoComment(comment.getTitle(), comment.getComment_txt(), comment.getId());
            optMongoReview.get().addComment(mongoComment);

            return ResponseEntity.ok(mongoCommentRepository.save(mongoComment));

        }
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MongoComment>> listCommentsForReview(@Valid@PathVariable("reviewId") Integer reviewId) {
        Optional<MongoReview> optionalMongoReview = mongoReviewRepository.findById(reviewId);

        return optionalMongoReview.map(mongoReview -> ResponseEntity.ok(optionalMongoReview.get().getCommentList())).orElseGet(() -> ResponseEntity.notFound().build());
    }
}