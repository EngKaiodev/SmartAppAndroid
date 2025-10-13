package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.domain.repository.ServicoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class CadastrarServicoViewModel extends ViewModel {

    private final ServicoRepository servicoRepository;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<Boolean> _added = new MutableLiveData<>();
    public LiveData<Boolean> added = _added;

    @Inject
    public CadastrarServicoViewModel(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }
    public void addServices(List<ServicoRequest> listToAdd) {
        disposables.add(servicoRepository.insertList(listToAdd).observeOn(AndroidSchedulers.mainThread()).subscribe(
                response -> {
                    if (response.code() == 201){
                        _added.postValue(true);
                    } else {
                        _added.postValue(false);
                    }
                },
                error -> {
                    _added.postValue(false);
                }
        )
        );
    }
}
