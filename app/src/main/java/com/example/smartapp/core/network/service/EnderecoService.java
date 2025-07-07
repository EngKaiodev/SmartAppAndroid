package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.request.EnderecoRequest;
import com.example.smartapp.core.domain.response.EnderecoResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EnderecoService {

    @POST("/Enderecos")
    Single<EnderecoResponse> createEndereco(
            @Body EnderecoRequest enderecoRequest
    );
}
