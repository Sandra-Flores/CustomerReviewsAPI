package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // Wire JPA repositories
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;

    @Autowired
    ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository){
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    /**
     * Creates a review for a product.
     *
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Review review) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            review.setProduct(optionalProduct.get());
            return ResponseEntity.ok(reviewRepository.save(review));
        }
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable("productId") Integer productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(!optionalProduct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.ok(reviewRepository.findAllByProduct(new Product(productId)));
    }
}