package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ReviewRequest;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReviewService {

    @POST("/review")
    Single<Response<Boolean>> save(
            @Body ReviewRequest request
    );
}
