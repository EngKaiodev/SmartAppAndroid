package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.HoursRequest;
import com.example.smartapp.core.domain.response.HourResponse;

import java.time.LocalDate;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HourService {

    @POST("/hour")
    Single<Response<HourResponse>> insertHour(
            @Body HoursRequest request
            );
    @POST("/hour/list")
    Single<Response<List<HourResponse>>> insertHourList(
            @Body List<HoursRequest> requestList
    );

    @GET("/hour/pro/{id}/{date}/opened")
    Single<Response<List<HourResponse>>> getByUserIdAndDateOpened(
            @Path("id") int id,
            @Path("date") LocalDate date
            );
}
