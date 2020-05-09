package com.udacity.course3.reviews;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.udacity.course3.reviews.entity.*;
import com.udacity.course3.reviews.repository.MongoCommentRepository;
import com.udacity.course3.reviews.repository.MongoReviewRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoReviewsAppTests {

    @Autowired
    private MongoReviewRepository mongoReviewRepository;
    @Autowired
    private MongoCommentRepository mongoCommentRepository;

    private MongoReview review1;
    private MongoComment comment1;

    private MongoReview review2;
    private MongoComment comment2;

    @Before
    public void setUp() {
        review1 = new MongoReview("Review one", "Review test text", 1);
        comment1 = new MongoComment("Comment one", "Comment test text", 1);

        review2 = new MongoReview("Review two", "Review two test text", 2);
        comment2 = new MongoComment("Comment two", "Comment two test text", 2);

        mongoReviewRepository.save(review1);
        mongoCommentRepository.save(comment1);

        mongoReviewRepository.save(review2);
        mongoCommentRepository.save(comment2);
    }

    @Test
    public void testDataWasAdded() {
        assertNotNull(mongoReviewRepository);
        assertNotNull(mongoCommentRepository);
    }

    @Test
    public void testMultiplesInserted() {

        List<MongoReview> optReview = mongoReviewRepository.findAll();
        List<MongoComment> optComment = mongoCommentRepository.findAll();

        assertTrue( optReview.size() > 1);
        assertTrue( optComment.size() > 1);

    }
}
