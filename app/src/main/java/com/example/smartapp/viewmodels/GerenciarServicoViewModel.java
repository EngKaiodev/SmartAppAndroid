package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.domain.repository.ServicoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class GerenciarServicoViewModel extends ViewModel {

    private final Preferences prefs;
    private final ServicoRepository servicoRepository;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<List<ServicoResponse>> _services = new MutableLiveData<>();
    public LiveData<List<ServicoResponse>> services = _services;

    @Inject
    public GerenciarServicoViewModel(Preferences prefs, ServicoRepository servicoRepository){
        this.servicoRepository = servicoRepository;
        this.prefs = prefs;
    }
    public void loadServices() {
        disposables.add(
          servicoRepository.getAllByUserId(prefs.getId()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                  response -> {
                      if (response.isSuccessful() && response.body() != null){
                          _services.postValue(response.body());
                      }
                  },
                  error -> {}
          )
        );
    }

    public void deleteService(int id) {
        try {
            disposables.add(
                    servicoRepository.delete(id).observeOn(AndroidSchedulers.mainThread()).subscribe(
                            response -> loadServices(),
                            error -> {}
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
