package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.domain.repository.ScheduleRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class ReagendarViewModel extends ViewModel {

    private final ScheduleRepository repository;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> _changed = new MutableLiveData<>();
    public LiveData<Boolean> changed = _changed;

    @Inject
    public ReagendarViewModel(ScheduleRepository repository) {
        this.repository = repository;
    }

    public void confirmSchedule(int id) {
        disposable.add(
                repository.confirmScheduleById(id).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful() && response.code() == 200) {
                                _changed.postValue(response.body());
                            }
                        },
                        error -> {
                        }
                )
        );
    }

    public void cancelSchedule(int id){
        disposable.add(
                repository.cancelScheduleById(id).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful() && response.code() == 200) {
                                _changed.postValue(response.body());
                            }
                        },
                        error -> {
                        }
                )
        );
    }

    public void finishSchedule(int id){
        disposable.add(
                repository.finishScheduleById(id).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful() && response.code() == 200) {
                                _changed.postValue(response.body());
                            }
                        },
                        error -> {
                        }
                )
        );
    }
}
