package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ClienteRequest;
import com.example.smartapp.core.domain.response.ClienteResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClienteService {

    @GET("/Clientes")
    public List<ClienteResponse> getAll();

    @GET("/CLientes/{id}")
    public ClienteResponse getById(
            @Path("id") int id
    );

    @POST("/Clientes")
    public ClienteResponse insert(
            @Body ClienteRequest clienteRequest
    );

    @DELETE("/Clientes/{id}")
    public void delete(
            @Path("id") int id
    );

}
