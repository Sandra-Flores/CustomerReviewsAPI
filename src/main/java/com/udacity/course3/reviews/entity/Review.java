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
    private boolean is_top_review;

    @ManyToOne
    private Product product;

    public Review(){};

    public Review(Integer id){
        this.id = id;
    }

    public Review(String title, String review_txt, Integer productId){
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

    public void setProduct(Product product) {
        this.product = product;
    }

    // GETTERS
    public Product getProduct() {
        return product;
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
        return is_top_review;
    }

    @Override
    public String toString(){
        return "Review{" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", reviewText = '" + review_txt + '\'' +
                ", recommended = " + is_top_review +
                ", product = " + product +
                '}';
    }
}
