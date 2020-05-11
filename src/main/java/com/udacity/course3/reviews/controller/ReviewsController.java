package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.MongoReview;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
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

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // Wire JPA repositories
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private MongoReviewRepository mongoReviewRepository;

    ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository,
                      MongoReviewRepository mongoReviewRepository){
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.mongoReviewRepository = mongoReviewRepository;
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
    public ResponseEntity<MongoReview> createReviewForProduct(@Valid @PathVariable("productId") Integer productId, @RequestBody Review review) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            review.setProduct(optionalProduct.get());
            review = reviewRepository.save(review);

            MongoReview mongoReview = new MongoReview(review.getTitle(), review.getReview_txt(), review.getId());
            return ResponseEntity.ok(mongoReviewRepository.save(mongoReview));
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
    public ResponseEntity<List<MongoReview>> listReviewsForProduct(@Valid @PathVariable("productId") Integer productId) {

        Product product = new Product(productId);
        List<MongoReview> mongoReviewList = new ArrayList<>();

        for (Review review : reviewRepository.findAllByProduct(product)) {
            Optional<MongoReview> optMongoReview = mongoReviewRepository.findById(review.getId());
                mongoReviewList.add(optMongoReview.get());
        }
        return ResponseEntity.ok(mongoReviewList);
    }
}