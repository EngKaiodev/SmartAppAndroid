package com.example.smartapp.core.repository;

import com.example.smartapp.core.domain.request.EnderecoRequest;
import com.example.smartapp.core.domain.response.EnderecoResponse;
import com.example.smartapp.core.network.service.EnderecoService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class EnderecoRepository {
    private final EnderecoService enderecoService;

    @Inject
    public EnderecoRepository(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    public Single<EnderecoResponse> create(EnderecoRequest request) {
        return enderecoService
                .createEndereco(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
