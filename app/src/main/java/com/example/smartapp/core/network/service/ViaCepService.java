package com.example.smartapp.core.network.service;

import com.example.smartapp.core.domain.response.ViaCepResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {
    @GET("{cep}/json/")
    Single<ViaCepResponse> buscarEndereco(@Path("cep") String cep);
}