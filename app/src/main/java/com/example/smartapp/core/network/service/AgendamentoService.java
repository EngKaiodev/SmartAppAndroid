package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.AgendamentoRequest;
import com.example.smartapp.core.domain.response.AgendamentoResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AgendamentoService {

    @GET("/Agendamentos")
    public List<AgendamentoResponse> getAll();

    @GET("/Agendamento/{id}")
    public AgendamentoResponse getById(
            @Path("id") int id
    );

    @POST("/Agendamentos")
    public AgendamentoResponse insert(
            @Body AgendamentoRequest agendamentoRequest
            );

    @DELETE("/Agendamentos/{id}")
    public void delete(
            @Path("id") int id
    );
}
