package com.example.smartapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.ScheduleRequest;
import com.example.smartapp.core.domain.response.HourResponse;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.domain.repository.HourRepository;
import com.example.smartapp.domain.repository.ScheduleRepository;
import com.example.smartapp.domain.repository.ServicoRepository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class AgendarServicoViewModel extends ViewModel {
    private final Preferences prefs;
    private final ServicoRepository servicoRepository;
    private final HourRepository hourRepository;
    private final ScheduleRepository scheduleRepository;

    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<ServicoResponse>> _servicos = new MutableLiveData<>();
    public LiveData<List<ServicoResponse>> servicos = _servicos;

    private MutableLiveData<List<HourResponse>> _hours = new MutableLiveData<>();
    public LiveData<List<HourResponse>> hours = _hours;

    private MutableLiveData<Boolean> _added = new MutableLiveData<>();
    public LiveData<Boolean> added = _added;

    private MutableLiveData<Boolean> _upgraded = new MutableLiveData<>();
    public LiveData<Boolean> upgraded = _upgraded;

    @Inject
    public AgendarServicoViewModel(ServicoRepository servicoRepository, HourRepository hourRepository, ScheduleRepository scheduleRepository, Preferences prefs) {
        this.servicoRepository = servicoRepository;
        this.hourRepository = hourRepository;
        this.scheduleRepository = scheduleRepository;
        this.prefs = prefs;
    }

    public void loadServicos(int id) {
        disposable.add(
                servicoRepository.getAllByUserId(id).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful() && response.code() == 200) {
                                _servicos.postValue(response.body());
                            }
                        },
                        error -> {
                        }
                )
        );
    }

    public void loadHours(int id, LocalDate date) {
        disposable.add(
                hourRepository.getByUserIdAndDate(id, date)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    if (response.isSuccessful() && response.code() == 200) {
                                        _hours.postValue(response.body());
                                    }
                                },
                                error -> {
                                    Log.e("AgendarServicoViewModel", error.getMessage());
                                }
                        )
        );
    }

    public void saveSchedule(int proId, ServicoResponse servicoResponse, HourResponse hourResponse) {
        disposable.add(
                scheduleRepository.save(new ScheduleRequest(prefs.getId(), proId, servicoResponse.getId(), hourResponse.getId()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    if (response.isSuccessful() && response.code() == 201) {
                                        _added.postValue(true);
                                    }
                                },
                                error -> {
                                }
                        )
        );
    }

    public void updateSchedule(ScheduleResponse scheduleResponse, HourResponse selectedItem) {
        disposable.add(
                scheduleRepository.update(scheduleResponse.getId(), selectedItem.getId())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    if (response.isSuccessful()) {
                                        _upgraded.postValue(true);
                                    }
                                },
                                error -> {
                                }
                        )
        );
    }
}
