package com.example.smartapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.ServicoEditRequest;
import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.domain.repository.ServicoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class CriarServicoViewModel extends ViewModel {

    private final ServicoRepository servicoRepository;
    private final Preferences prefs;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<Boolean> _edited = new MutableLiveData<>();
    public LiveData<Boolean> edited = _edited;

    private MutableLiveData<Boolean> _added = new MutableLiveData<>();
    public LiveData<Boolean> added = _added;

    @Inject
    public CriarServicoViewModel(ServicoRepository servicoRepository, Preferences prefs) {
        this.servicoRepository = servicoRepository;
        this.prefs = prefs;
    }

    public void editService(ServicoResponse serviceToAdd, String name, String description, String value, String duration) {
        disposables.add(
                servicoRepository.edit(new ServicoEditRequest(
                                        serviceToAdd.getId(),
                                        name,
                                        description,
                                        Float.parseFloat(value),
                                        Integer.parseInt(duration)
                                )
                        )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    if (response.isSuccessful() && response.body() != null) {
                                        _edited.postValue(true);
                                    }
                                },
                                error -> {
                                }
                        )
        );
    }

    public void addService(String name, String description, String value, String duration) {
        try{
            disposables.add(
                    servicoRepository.insert(new ServicoRequest(
                                            name,
                                            description,
                                            Float.parseFloat(value),
                                            Integer.parseInt(duration),
                                            prefs.getId()
                                    )
                            )
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    response -> {
                                        if (response.isSuccessful() && response.body() != null) {
                                            _added.postValue(true);
                                        }
                                    },
                                    error -> {
                                    }
                            )
            );
        } catch (Exception e) {
            Log.e("Adicionar Servicco", "Estourou aqui: " + e);
        }
    }

    public boolean validateField(
            String name,
            String descricao,
            String preco,
            String tempo
    ) {
        if (name == null || name.trim().isEmpty()) return false;
        if (descricao == null || descricao.trim().isEmpty()) return false;

        try {
            double precoValue = Double.parseDouble(preco);
            if (precoValue <= 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            int tempoValue = Integer.parseInt(tempo);
            if (tempoValue <= 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
