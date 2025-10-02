package com.example.smartapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.domain.response.ProfissionalDetailResponse;
import com.example.smartapp.domain.repository.ProfissionalRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

@HiltViewModel
public class PerfilPrestadorViewModel extends ViewModel {

    private final ProfissionalRepository profissionalRepository;

    @Inject
    public PerfilPrestadorViewModel(ProfissionalRepository profissionalRepository){
        this.profissionalRepository = profissionalRepository;
    }
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<ProfissionalDetailResponse> _profissional = new MutableLiveData<>();
    public LiveData<ProfissionalDetailResponse> profissional = _profissional;

    public void getDetails(int id) {
        try{
            disposables.add(
                    profissionalRepository.getDetailById(id)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    response -> _profissional.setValue(response.body()),
                                    throwable -> _profissional.setValue(null)
                            )
            );
        } catch (Exception e){
            Log.e("PerfilPrestadorViewModel", "Erro: " + e);
        }
    }
}
