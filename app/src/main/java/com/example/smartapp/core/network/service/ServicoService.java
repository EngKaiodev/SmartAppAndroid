package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.core.domain.response.ServicoResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicoService {

    @GET("/Servicos")
    public List<ServicoResponse> getAll();

    @GET("/Servicos/{id}")
    public ServicoResponse getById(
            @Path("id") int id
    );

    @POST("/Servicos")
    public ServicoResponse insert(
            @Body ServicoRequest servicoRequest
    );

    @DELETE("/Servicos/{id}")
    public void delete(
            @Path("id") int id
    );
}
