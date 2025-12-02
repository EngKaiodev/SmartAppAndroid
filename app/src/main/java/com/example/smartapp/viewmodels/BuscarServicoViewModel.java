package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.core.domain.response.ServiceCompleteResponse;
import com.example.smartapp.domain.repository.ProfissionalRepository;
import com.example.smartapp.domain.repository.ServicoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


@HiltViewModel
public class BuscarServicoViewModel extends ViewModel {

    private final ServicoRepository servicoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final Preferences prefs;

    @Inject
    public BuscarServicoViewModel(ServicoRepository servicoRepository, ProfissionalRepository profissionalRepository, Preferences prefs) {
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
        this.prefs = prefs;
    }

    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<ServiceCompleteResponse>> _services = new MutableLiveData<>();
    public LiveData<List<ServiceCompleteResponse>> servicos = _services;

    private MutableLiveData<ProfissionalResponse> _pro = new MutableLiveData<>();
    public LiveData<ProfissionalResponse> pro = _pro;
    public void loadServicos(){
        disposable.add(
                servicoRepository.getAll().subscribeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful()){

                                List<ServiceCompleteResponse> original = response.body();
                                if (prefs.getType() == 1 && original != null){
                                    original = original.stream().filter( x ->
                                            x.getProfissionalId() != prefs.getId()
                                    ).toList();
                                }
                                _services.postValue(original);
                            }
                        }
                )
        );
    }

    public void loadPro(int id) throws Exception {
        disposable.add(
                profissionalRepository.getDetailById(id).subscribeOn(AndroidSchedulers.mainThread()).subscribe(
                        response -> {
                            if (response.isSuccessful()){
                                _pro.postValue(response.body().getProfissional());
                            }
                        }
                )
        );
    }

    public void clearPro(){
        _pro.postValue(null);
    }
}
