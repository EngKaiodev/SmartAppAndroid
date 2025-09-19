package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.UserRequest;
import com.example.smartapp.core.domain.response.UserResponse;

import dagger.Provides;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthService {

    @POST("auth/register")
   Single<Response<UserResponse>> register(
            @Body UserRequest userRequest
    );
}
