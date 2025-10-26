package com.example.smartapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.ReviewRequest;
import com.example.smartapp.domain.repository.ReviewRepository;
import com.example.smartapp.domain.repository.ScheduleRepository;

import java.time.LocalDateTime;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class AvaliarServicoViewModel extends ViewModel {

    private final ReviewRepository reviewRepository;
    private final Preferences preferences;

    private final ScheduleRepository scheduleRepository;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> _sent = new MutableLiveData<>();
    public LiveData<Boolean> sent = _sent;

    private final MutableLiveData<Boolean> _reviwed = new MutableLiveData<>();
    public LiveData<Boolean> reviwed = _reviwed;

    @Inject
    public AvaliarServicoViewModel(ReviewRepository reviewRepository, Preferences preferences, ScheduleRepository scheduleRepository) {
        this.reviewRepository = reviewRepository;
        this.preferences = preferences;
        this.scheduleRepository = scheduleRepository;
    }

    public void sendReview(
            int proId,
            int rating,
            int scheduleId,
            String comment
    ) {
        disposable.add(
                reviewRepository.save(new ReviewRequest(
                                proId,
                                preferences.getId(),
                                rating,
                                comment,
                                LocalDateTime.now(),
                                scheduleId
                        )
                ).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful() && response.code() == 201) {
                                _sent.postValue(response.body());
                            }
                        },
                        error -> Log.e("ReviewViewModel", "Erro ao enviar review", error)

                )
        );
    }

    public void reviewSchedule(int id){
        disposable.add(
                scheduleRepository.reviewScheduleById(id).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful()){
                                _reviwed.postValue(response.body());
                            }
                        },
                        error -> {}
                )
        );
    }
}
