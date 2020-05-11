package com.udacity.course3.reviews.entity;

import javax.persistence.*;

public class MongoComment {

    @Id
    private Integer id;
    private String title;
    private String comment_txt;

    public MongoComment(){};

    public MongoComment(Integer id){
        this.id = id;
    }

    public MongoComment(String title, String comment_txt, Integer reviewId){
        this.title = title;
        this.comment_txt = comment_txt;
        this.id = reviewId;
    }

    // SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment_txt(String comment_txt) {
        this.comment_txt = comment_txt;
    }

    // GETTERS
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getComment_txt() {
        return comment_txt;
    }

    @Override
    public String toString(){
        return String.format(
                "Comment[id=%s, title='%s', comment_txt='%s']",
                id, title, comment_txt);

    }
}
