package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ProEditRequest;
import com.example.smartapp.core.domain.response.MyselfResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClienteService {

    @GET("/cliente/myself")
    Single<Response<MyselfResponse>> getMyself();

    @POST("/cliente/myself")
    Single<Response<MyselfResponse>> editMyself(
            @Body ProEditRequest request
    );


}
