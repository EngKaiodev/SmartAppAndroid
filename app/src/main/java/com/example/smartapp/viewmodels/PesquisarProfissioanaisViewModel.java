package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.domain.repository.ProfissionalRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class PesquisarProfissioanaisViewModel extends ViewModel {

    private final ProfissionalRepository profissionalRepository;

    @Inject
    public PesquisarProfissioanaisViewModel(ProfissionalRepository profissionalRepository){
        this.profissionalRepository = profissionalRepository;
    }

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<List<ProfissionalResponse>> _pros = new MutableLiveData<>();
    public LiveData<List<ProfissionalResponse>> pros = _pros;

    public void loadPros(){
        disposables.add(
                profissionalRepository.getAll().observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful()){
                                _pros.postValue(response.body());
                            }
                        }
                )
        );
    }
}
