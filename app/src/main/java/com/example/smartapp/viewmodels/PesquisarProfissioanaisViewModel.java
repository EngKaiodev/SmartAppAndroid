package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
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
    private final Preferences prefs;

    @Inject
    public PesquisarProfissioanaisViewModel(ProfissionalRepository profissionalRepository, Preferences prefs){
        this.profissionalRepository = profissionalRepository;
        this.prefs = prefs;
    }

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<List<ProfissionalResponse>> _pros = new MutableLiveData<>();
    public LiveData<List<ProfissionalResponse>> pros = _pros;

    public void loadPros(){
        disposables.add(
                profissionalRepository.getAll().observeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful()){

                                List<ProfissionalResponse> original = response.body();
                                if (prefs.getType() == 1 && original != null){
                                    original = original.stream().filter( x ->
                                            x.getId_profissional() != prefs.getId()
                                    ).toList();
                                }

                                _pros.postValue(original);
                            }
                        }
                )
        );
    }
}
