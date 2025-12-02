package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.request.ScheduleRequest;
import com.example.smartapp.core.domain.response.HistoricResponse;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.core.network.service.ScheduleService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ScheduleRepository {

    private final ScheduleService service;

    @Inject
    public ScheduleRepository(ScheduleService service) {
        this.service = service;
    }

    public Single<Response<ScheduleResponse>> save(ScheduleRequest request) {
        return service.save(request).subscribeOn(Schedulers.io());
    }

    public Single<Response<Boolean>> update(int schedule, int hourId, int notId) {
        return service.update(schedule, hourId, notId).subscribeOn(Schedulers.io());
    }

    public Single<Response<List<ScheduleResponse>>> getAllByUserId(int id) {
        return service.getAllByUserId(id).subscribeOn(Schedulers.io());
    }

    public Single<Response<List<ScheduleResponse>>> getAllByProId(int id) {
        return service.getAllByProId(id).subscribeOn(Schedulers.io());
    }

    public Single<Response<Boolean>> confirmScheduleById(int id){
        return service.confirmScheduleById(id).subscribeOn(Schedulers.io());
    }

    public Single<Response<Boolean>> cancelScheduleById(int id, int notifyId){
        return service.cancelScheduleById(id, notifyId).subscribeOn(Schedulers.io());
    }
    public Single<Response<Boolean>> reviewScheduleById(int id){
        return service.reviewScheduleById(id).subscribeOn(Schedulers.io());
    }
    public Single<Response<Boolean>> finishScheduleById(int id, int notifyId){
        return service.finishScheduleById(id, notifyId).subscribeOn(Schedulers.io());
    }

    public Single<Response<List<HistoricResponse>>> getHistoric(String type, int id){
        return service.getHistoric(type, id).subscribeOn(Schedulers.io());
    }
}
