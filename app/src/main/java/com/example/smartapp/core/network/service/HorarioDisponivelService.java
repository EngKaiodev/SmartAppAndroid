package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.HorarioDisponivelRequest;
import com.example.smartapp.core.domain.response.HorarioDisponivelResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HorarioDisponivelService {

    @GET("/Horarios")
    public List<HorarioDisponivelResponse> getAll();

    @GET("/Horarios/{id}")
    public HorarioDisponivelResponse getById(
            @Path("id") int id
    );

    @POST("Horarios")
    public HorarioDisponivelResponse insert(
            @Body HorarioDisponivelRequest horarioDisponivelRequest
            );

    @DELETE("/Horarios/{id}")
    public void delete(
            @Path("id") int id
    );
}
