package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.response.HistoricResponse;
import com.example.smartapp.domain.repository.ScheduleRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class HistoricoServicoViewModel extends ViewModel {

    private final ScheduleRepository scheduleRepository;
    private final Preferences prefs;

    @Inject
    public HistoricoServicoViewModel(ScheduleRepository scheduleRepository, Preferences prefs) {
        this.scheduleRepository = scheduleRepository;
        this.prefs = prefs;
    }

    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<HistoricResponse>> _historicos = new MutableLiveData<>();
    public LiveData<List<HistoricResponse>> historicos = _historicos;

    public void loadHistoricos() {
        String type = prefs.getType() == 1 ? "pro" : "cli";
        disposable.add(
                scheduleRepository.getHistoric(type, prefs.getId())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                listResponse -> {
                                    if (listResponse.isSuccessful() && listResponse.code() == 200) {
                                        _historicos.postValue(listResponse.body());
                                    }
                                }
                        )
        );
    }
}
