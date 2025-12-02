package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.domain.response.AllReviewResponse;
import com.example.smartapp.domain.repository.ReviewRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class AvaliarTelaViewModel extends ViewModel {

    private final ReviewRepository reviewRepository;

    @Inject
    public AvaliarTelaViewModel(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private final CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<AllReviewResponse> _reviews = new MutableLiveData<>();
    public LiveData<AllReviewResponse> reviews = _reviews;

    public void loadReviews(int proId){
        disposable.add(
                reviewRepository.getAllReview(proId).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful() && response.code() == 200){
                                _reviews.postValue(response.body());
                            }
                        }
                )
        );
    }
}
