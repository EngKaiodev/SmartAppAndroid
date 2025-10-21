package com.example.smartapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.domain.response.HourResponse;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.domain.repository.HourRepository;
import com.example.smartapp.domain.repository.ServicoRepository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class AgendarServicoViewModel extends ViewModel {

    private final ServicoRepository servicoRepository;
    private final HourRepository hourRepository;

    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<ServicoResponse>> _servicos = new MutableLiveData<>();
    public LiveData<List<ServicoResponse>> servicos = _servicos;

    private MutableLiveData<List<HourResponse>> _hours = new MutableLiveData<>();
    public LiveData<List<HourResponse>> hours = _hours;

    @Inject
    public AgendarServicoViewModel(ServicoRepository servicoRepository, HourRepository hourRepository) {
        this.servicoRepository = servicoRepository;
        this.hourRepository = hourRepository;
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

    public void loadHours(int id, LocalDate date){
        disposable.add(
                hourRepository.getByUserIdAndDate(id, date)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    if (response.isSuccessful() && response.code() == 200){
                                        _hours.postValue(response.body());
                                    }
                                },
                                error -> {
                                    Log.e("AgendarServicoViewModel", error.getMessage());
                                }
                        )
        );
    }
}
