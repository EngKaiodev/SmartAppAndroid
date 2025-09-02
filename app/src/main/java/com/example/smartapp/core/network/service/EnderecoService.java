package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.EnderecoRequest;
import com.example.smartapp.core.domain.response.EnderecoResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EnderecoService {

    @POST("/Enderecos")
    Single<EnderecoResponse> createEndereco(
            @Body EnderecoRequest enderecoRequest
    );

    @GET("/Enderecos")
    public List<EnderecoResponse> getAll();

    @GET("/Enderecos/{id}")
    public EnderecoResponse getById(
            @Path("id") int id
    );

    @POST("/Enderecos")
    public EnderecoResponse insert(
            @Body EnderecoRequest enderecoRequest
    );

    @DELETE("/Enderecos/id")
    public void delete(
            @Path("id") int id
    );



}
