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

    @POST("/schedules/{schedule}/{hour}/{notId}")
    Single<Response<Boolean>> update(
            @Path("schedule") int schedule,
            @Path("hour") int hourId,
            @Path ("notId") int notId
    );

    @GET("/schedules/user/{id}")
    Single<Response<List<ScheduleResponse>>> getAllByUserId(
            @Path("id") int id
    );

    @GET("/schedules/pro/{id}")
    Single<Response<List<ScheduleResponse>>> getAllByProId(
            @Path("id") int id
    );

    @POST("/schedules/{id}/confirm")
    Single<Response<Boolean>> confirmScheduleById(
            @Path("id") int id
    );

    @POST("/schedules/{id}/cancel/{notifyId}")
    Single<Response<Boolean>> cancelScheduleById(
            @Path("id") int id,
            @Path("notifyId") int notifyId
    );

    @POST("/schedules/{id}/review")
    Single<Response<Boolean>> reviewScheduleById(
            @Path("id") int id
    );

    @POST("/schedules/{id}/finish/{notifyId}")
    Single<Response<Boolean>> finishScheduleById(
            @Path("id") int id,
            @Path("notifyId") int notifyId
    );
}
