package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ReviewRepository
 */

@Repository
public interface ReviewRepository extends JpaRepository <Review, Integer> {

    //Find all reviews for a product

    List<Review> findAllByProduct(Product product);
}
