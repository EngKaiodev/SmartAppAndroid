package com.example.smartapp.core.domain.response;

import java.time.LocalDateTime;

public class ReviewToList {
    private String name;
    private String comment;
    private LocalDateTime date;
    private int review;

    public ReviewToList() {
    }

    public ReviewToList(String name, String comment, LocalDateTime date, int review) {
        this.name = name;
        this.comment = comment;
        this.date = date;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public ReviewSimpleResponse toResponse() {
        return new ReviewSimpleResponse(
                this.name,
                this.comment,
                this.date,
                this.review
        );
    }

}
