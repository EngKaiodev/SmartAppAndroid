package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.HoursRequest;
import com.example.smartapp.core.domain.response.HourResponse;
import com.example.smartapp.domain.repository.HourRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class GerenciarHorariosViewModel extends ViewModel {

    private final HourRepository hourRepository;
    private final Preferences prefs;

    @Inject
    public GerenciarHorariosViewModel(HourRepository hourRepository, Preferences prefs) {
        this.hourRepository = hourRepository;
        this.prefs = prefs;
    }

    private final CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<Boolean> _add = new MutableLiveData<>();
    public LiveData<Boolean> add = _add;

    public void insertHourList(List<HoursRequest> list) {
        disposables.add(
                hourRepository.insertList(list)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    if (response.body() != null && !response.body().isEmpty()) {
                                        _add.setValue(true);
                                    }
                                },
                                throwable -> _add.setValue(false)
                        )
        );
    }

    public int getId() {
        return prefs.getId();
    }
}
