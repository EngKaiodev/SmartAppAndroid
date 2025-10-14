package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.ServicoEditRequest;
import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.core.domain.response.ServicoResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicoService {

    @GET("/services")
    Single<Response<List<ServicoResponse>>> getAll();

    @GET("/services/{id}")
    Single<Response<ServicoResponse>> getById(
            @Path("id") int id
    );

    @POST("/services")
    Single<Response<ServicoResponse>> insert(
            @Body ServicoRequest servicoRequest
    );

    @POST("/services/list")
    Single<Response<List<ServicoResponse>>> insertList(
            @Body List<ServicoRequest> listToAdd
    );

    @DELETE("/services/{id}")
    Single<Response<Void>> delete(
            @Path("id") int id
    );

    @GET("/services/all/{id}")
    Single<Response<List<ServicoResponse>>> getAllByUserId(
            @Path("id") int id
    );

    @PATCH("/services/{id}")
    Single<Response<ServicoResponse>> edit(
            @Path("id") int id,
            @Body ServicoEditRequest request
    );
}
