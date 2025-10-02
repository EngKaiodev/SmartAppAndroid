package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ProfissionalRequest;
import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.core.domain.response.ProfissionalDetailResponse;
import com.example.smartapp.core.domain.response.ProfissionalResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProfissionalService {

    @GET("/profissional")
    Single<Response<List<ProfissionalResponse>>> getAll();

    @GET("/profissional/{id}")
    Single<Response<ProfissionalDetailResponse>> GetDetailById(
            @Path("id") int id
    );

    @POST("/profissional")
    ProfissionalResponse insert(
            @Body ProfissionalRequest profissionalRequest
    );

    @GET("/profissional/myself")
    Single<Response<MyselfResponse>> getMyself();
}
