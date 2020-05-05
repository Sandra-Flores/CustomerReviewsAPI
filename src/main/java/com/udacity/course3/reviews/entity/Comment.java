package com.udacity.course3.reviews.entity;

import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Integer likes;
    private String comment_txt;

    @ManyToOne
    private Review review;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setComment_txt(String comment_txt) {
        this.comment_txt = comment_txt;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getLikes() {
        return likes;
    }

    public String getComment_txt() {
        return comment_txt;
    }
}
