package com.example.smartapp.domain.repository;

import android.util.Log;

import com.example.smartapp.core.domain.request.ProEditRequest;
import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.core.network.service.ClienteService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@Singleton
public class ClienteRepository {

    private final ClienteService clienteService;

    @Inject
    public ClienteRepository(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Single<Response<MyselfResponse>> getMyself(){
        return clienteService.getMyself().subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    Log.i("ClienteRepository", "Informação recuperada com sucesso, com " + response.body());
                }).doOnError(error -> {
                    Log.i("ClienteRepository", "Erro ao recuperar informação: " + error.getLocalizedMessage());
                });
    }
    public Single<Response<MyselfResponse>> editMyself(ProEditRequest request){
        return clienteService.editMyself(request).subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    Log.i("ClienteRepository", "Informação editada com sucesso: " + response.body());
                })
                .doOnError(error -> {
                    Log.i("ClienteRepository", "Erro ao editar informação: " + error.getLocalizedMessage());
                });
    }
}
