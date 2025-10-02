package com.example.smartapp.core.domain.response;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class ReviewSimpleResponse {
    @SerializedName("complete_name")
    private String completeName;
    private String comment;
    private String date;
    private int review;

    public ReviewSimpleResponse(String completeName, String comment, String date, int review) {
        this.completeName = completeName;
        this.comment = comment;
        this.date = date;
        this.review = review;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
