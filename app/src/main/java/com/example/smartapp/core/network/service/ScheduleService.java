package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ScheduleRequest;
import com.example.smartapp.core.domain.response.ScheduleResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ScheduleService {

    @POST("/schedules")
    Single<Response<ScheduleResponse>> save(
            @Body ScheduleRequest request
    );

    @GET("/schedules/user/{id}")
    Single<Response<List<ScheduleResponse>>> getAllByUserId(
            @Path("id") int id
    );

    @GET("/schedules/pro/{id}")
    Single<Response<List<ScheduleResponse>>> getAllByProId(
            @Path("id") int id
    );
}
