package com.udacity.course3.reviews;

import static org.junit.Assert.*;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired private ProductRepository productRepository;
	@Autowired private ReviewRepository reviewRepository;
	@Autowired private CommentRepository commentRepository;

	private Product product1;
	private Product product2;

	private Review review1;
	private Review review2;

	private Comment comment1;


	@Before
	public void setUp() {
		product1 = new Product("Product Name", "Test Description");
		product2 = new Product("Another Product Name", "Product Two Test Description");

		review1 = new Review("Review Title", "Review Description", 1);
		review2 = new Review("Review2 Title", "Review Description2",2);

		comment1 = new Comment("Comment Title", "Comment Description", 1);
	}

	@Test
	public void saveProductAndFindById(){
		productRepository.save(product1);
		Optional<Product> optionalProduct = productRepository.findById(product1.getId());

		assertNotNull(optionalProduct);
	}


	@Test
	public void AddReviewToProduct() {
		review1.setProduct(product1);
		comment1.setReview(review1);

		Optional<Product> optionalProduct = productRepository.findById(1);
		Optional<Review> optionalReview = reviewRepository.findById(1);
		Optional<Comment> optionalComment = commentRepository.findById(1);

		assertNotNull(optionalProduct);
		assertNotNull(optionalReview);
		assertNotNull(optionalComment);
	}

	@Test
	public void AddMultipleReviewsToProduct() {
		productRepository.save(product1);

		review1.setProduct(product1);
		review2.setProduct(product1);

		reviewRepository.save(review1);
		reviewRepository.save(review2);

		Optional<Product> optionalProduct = productRepository.findById(1);
		Optional<Review> optionalReview = reviewRepository.findById(1);
		Optional<Review> optionalReview2 = reviewRepository.findById(2);

		System.out.println("-- REVIEWS --" + reviewRepository.findAllByProduct(product1));

		assertNotNull(optionalProduct);
		assertTrue(optionalReview.isPresent());
		assertTrue(optionalReview2.isPresent());
	}
}