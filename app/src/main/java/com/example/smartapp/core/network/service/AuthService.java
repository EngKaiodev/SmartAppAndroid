package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.LoginRequest;
import com.example.smartapp.core.domain.request.RefreshRequest;
import com.example.smartapp.core.domain.request.UserRequest;
import com.example.smartapp.core.domain.response.RefreshResponse;
import com.example.smartapp.core.domain.response.UserResponse;

import dagger.Provides;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthService {

    @POST("auth/register")
    Single<Response<UserResponse>> register(
            @Body UserRequest userRequest
    );

    @POST("auth/login")
    Single<Response<UserResponse>> login(
            @Body LoginRequest loginRequest
    );

    @POST("auth/refresh")
    Call<RefreshResponse> refresh(
            @Body RefreshRequest refreshRequest
    );
}
