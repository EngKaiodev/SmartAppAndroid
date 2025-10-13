package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.core.network.service.ServicoService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ServicoRepository {

    private final ServicoService servicoService;

    @Inject
    public ServicoRepository(ServicoService servicoService){
        this.servicoService = servicoService;
    }

    public Single<Response<List<ServicoResponse>>> getAll(){return servicoService.getAll().observeOn(Schedulers.io());}

    public Single<Response<ServicoResponse>> getById(int id) throws Exception{
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }
        return servicoService.getById(id).subscribeOn(Schedulers.io());
    }

    public Single<Response<ServicoResponse>> insert(ServicoRequest servicoRequest) throws Exception{
        return servicoService.insert(servicoRequest).observeOn(Schedulers.io());
    }

    public Single<Response<Void>> delete(int id) throws Exception{
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }
        return servicoService.delete(id).subscribeOn(Schedulers.io());
    }

    public Single<Response<List<ServicoResponse>>> insertList(List<ServicoRequest> listToAdd) {
        return servicoService.insertList(listToAdd).subscribeOn(Schedulers.io());
    }
}
