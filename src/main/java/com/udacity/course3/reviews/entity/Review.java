package com.udacity.course3.reviews.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String review_txt;
    private boolean top_review;

    @ManyToOne
    private Product product;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReview_txt(String review_txt) {
        this.review_txt = review_txt;
    }

    public void setTop_review(boolean top_review) {
        this.top_review = top_review;
    }

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
        return top_review;
    }
}
