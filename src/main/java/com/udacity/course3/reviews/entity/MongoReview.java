package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MongoReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String review_txt;
    private boolean is_top_review;
    private List<MongoComment> CommentsList;

    public MongoReview(){};

    public MongoReview(Integer id){
        this.id = id;
    }

    public MongoReview(String title, String review_txt, Integer productId){
        this.title = title;
        this.review_txt = review_txt;
        this.id = productId;
    }

    // SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReview_txt(String review_txt) {
        this.review_txt = review_txt;
    }

    public void setTop_review(boolean is_top_review) {
        this.is_top_review = is_top_review;
    }

    // GETTERS
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReview_txt() {
        return review_txt;
    }

    public boolean isTop_review() {
        return is_top_review;
    }

    @Override
    public String toString(){
        return String.format(
                "Review[id=%s, title='%s', review_txt='%s', is_top_review='%s',]",
                id, title, review_txt, is_top_review);
    }
}
