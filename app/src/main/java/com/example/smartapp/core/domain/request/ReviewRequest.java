package com.example.smartapp.core.domain.request;

import java.time.LocalDateTime;

public class ReviewRequest {
    private int pro_id;
    private int cli_id;
    private int review;
    private String comment;
    private LocalDateTime date;
    private int schedule_id;

    public ReviewRequest() {
    }

    public ReviewRequest(int pro_id, int cli_id, int review, String comment, LocalDateTime date, int schedule_id) {
        this.pro_id = pro_id;
        this.cli_id = cli_id;
        this.review = review;
        this.comment = comment;
        this.date = date;
        this.schedule_id = schedule_id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
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
}
