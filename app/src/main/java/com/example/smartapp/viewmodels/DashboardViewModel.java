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
public class DashboardViewModel extends ViewModel {

    private final ProfissionalRepository profissionalRepository;

    @Inject
    public DashboardViewModel(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    private final MutableLiveData<List<ProfissionalResponse>> _profissionais = new MutableLiveData<>();
    public LiveData<List<ProfissionalResponse>> profissionais = _profissionais;

    private final CompositeDisposable disposables = new CompositeDisposable();
    public void loadProfissionais() {
        disposables.add(
            profissionalRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> _profissionais.setValue(response.body()),
                        throwable -> {
                            _profissionais.setValue(List.of());
                        }
                )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
