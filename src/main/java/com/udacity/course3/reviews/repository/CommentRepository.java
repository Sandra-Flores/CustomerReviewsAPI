package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CommentRepository
 */

@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer> {

    // Find all comments for review

    List<Comment> findAllByReview(Review review);


}