package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ProfissionalRequest;
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

    @GET("/Profissionais")
    public Single<Response<List<ProfissionalResponse>>> getAll();

    @GET("/Profissionais/{id}")
    public ProfissionalResponse GetById(
            @Path("id") int id
    );

    @POST("/Profissionais")
    public ProfissionalResponse insert(
            @Body ProfissionalRequest profissionalRequest
            );

    @DELETE("/Profissionais/{id}")
    public void delete(
            @Path("id") int id
    );
}
