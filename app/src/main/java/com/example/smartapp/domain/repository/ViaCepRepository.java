package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.response.ViaCepResponse;
import com.example.smartapp.core.network.service.ViaCepService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class ViaCepRepository {

    private final ViaCepService viaCepService;

    @Inject
    public ViaCepRepository(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    public Single<ViaCepResponse> buscarEnderecoPorCep(String cep) {
        return viaCepService.buscarEndereco(cep);
    }
}
