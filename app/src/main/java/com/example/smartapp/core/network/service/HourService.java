package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.HoursRequest;
import com.example.smartapp.core.domain.response.HourResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HourService {

    @POST("/hour")
    Single<Response<HourResponse>> insertHour(
            @Body HoursRequest request
            );
    @POST("/hour")
    Single<Response<List<HourResponse>>> insertHourList(
            @Body List<HoursRequest> requestList
    );
}
