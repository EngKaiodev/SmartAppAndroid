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
public class DashboardViewModel extends ViewModel {

    private final ProfissionalRepository profissionalRepository;
    private final Preferences prefs;

    @Inject
    public DashboardViewModel(ProfissionalRepository profissionalRepository, Preferences preferences) {
        this.profissionalRepository = profissionalRepository;
        this.prefs = preferences;
    }

    private final MutableLiveData<List<ProfissionalResponse>> _profissionais = new MutableLiveData<>();
    public LiveData<List<ProfissionalResponse>> profissionais = _profissionais;

    private final CompositeDisposable disposables = new CompositeDisposable();
    public void loadProfissionais() {
        disposables.add(
            profissionalRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<ProfissionalResponse> original = response.body();
                            if (prefs.getType() == 1){
                                original = original.stream().filter( x ->
                                    x.getId_profissional() != prefs.getId()
                                ).toList();
                            }

                            _profissionais.postValue(original);

                        },
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

    public String getUserName() {
        return prefs.getName();
    }
}
