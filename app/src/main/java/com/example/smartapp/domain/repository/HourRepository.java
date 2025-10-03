package com.example.smartapp.domain.repository;

import android.util.Log;

import com.example.smartapp.core.domain.request.HoursRequest;
import com.example.smartapp.core.domain.response.HourResponse;
import com.example.smartapp.core.network.service.HourService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HourRepository {

    private final HourService hourService;

    @Inject
    public HourRepository(HourService hourService){
        this.hourService = hourService;
    }

    public Single<Response<HourResponse>> insert(HoursRequest request){
        return hourService.insertHour(request).subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    Log.i("HourRepository", "Horario inserido com sucesso");
                })
                .doOnError(error -> {
                    Log.e("HourRepository", "Erro ao inserir horário: " + error.getLocalizedMessage());
                });
    }

    public Single<Response<List<HourResponse>>> insertList(List<HoursRequest> request){
        return hourService.insertHourList(request).subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    Log.i("HourRepository", "Horario inserido com sucesso");
                })
                .doOnError(error -> {
                    Log.e("HourRepository", "Erro ao inserir horário: " + error.getLocalizedMessage());
                });
    }
}
