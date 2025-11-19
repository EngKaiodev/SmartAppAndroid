package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.domain.repository.ScheduleRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class GerenciarAgendamentoViewModel extends ViewModel {

    private final ScheduleRepository scheduleRepository;
    private final Preferences prefs;


    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<ScheduleResponse>> _schedules = new MutableLiveData<>();
    public LiveData<List<ScheduleResponse>> schedules = _schedules;

    @Inject
    public GerenciarAgendamentoViewModel(ScheduleRepository scheduleRepository, Preferences prefs) {
        this.scheduleRepository = scheduleRepository;
        this.prefs = prefs;
    }

    public void loadSchedulers(){
        if (prefs.getType() == 1){
            disposable.add(
                    scheduleRepository.getAllByProId(prefs.getId()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                            response -> {
                                if (response.isSuccessful() && response.code() == 200){
                                    _schedules.postValue(response.body());
                                }
                            },
                            error -> {}
                    )
            );
        } else {
            disposable.add(
                    scheduleRepository.getAllByUserId(prefs.getId()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                            response -> {
                                if (response.isSuccessful() && response.code() == 200){
                                    _schedules.postValue(response.body());
                                }
                            },
                            error -> {}
                    )
            );
        }

    }

    public int getType() {
        return prefs.getType();
    }

    public int getId() {
        return prefs.getId();
    }
}
