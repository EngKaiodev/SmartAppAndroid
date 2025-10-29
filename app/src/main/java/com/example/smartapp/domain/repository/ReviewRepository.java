package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.request.ReviewRequest;
import com.example.smartapp.core.domain.response.AllReviewResponse;
import com.example.smartapp.core.network.service.ReviewService;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ReviewRepository {

    private final ReviewService service;

    @Inject
    public ReviewRepository(ReviewService service){
        this.service = service;
    }

    public Single<Response<Boolean>> save(ReviewRequest request){
        return service.save(request).subscribeOn(Schedulers.io());
    }

    public Single<Response<AllReviewResponse>> getAllReview(int proId){
        return service.getReviewToAll(proId).subscribeOn(Schedulers.io());
    }
}
