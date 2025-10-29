package com.example.smartapp.core.domain.response;

import java.util.List;

public class AllReviewResponse {
    private ReviewData data;
    private List<ReviewToList> reviewsList;

    public AllReviewResponse() {
    }

    public AllReviewResponse(ReviewData data, List<ReviewToList> reviewsList) {
        this.data = data;
        this.reviewsList = reviewsList;
    }

    public ReviewData getData() {
        return data;
    }

    public void setData(ReviewData data) {
        this.data = data;
    }

    public List<ReviewToList> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<ReviewToList> reviewsList) {
        this.reviewsList = reviewsList;
    }
}
