package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Mask;
import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.ProEditRequest;
import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.core.domain.response.ViaCepResponse;
import com.example.smartapp.domain.repository.ClienteRepository;
import com.example.smartapp.domain.repository.ViaCepRepository;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class EditarClienteViewModel extends ViewModel {

    private Preferences prefs;
    private ClienteRepository repository;
    private ViaCepRepository viaCepRepository;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<MyselfResponse> _myself = new MutableLiveData<>();
    public LiveData<MyselfResponse> myself = _myself;

    private final MutableLiveData<Boolean> _edited = new MutableLiveData<>();
    public LiveData<Boolean> edited = _edited;

    private final MutableLiveData<ViaCepResponse> _address = new MutableLiveData<>();
    public LiveData<ViaCepResponse> address = _address;

    @Inject
    public EditarClienteViewModel(Preferences prefs, ClienteRepository repository, ViaCepRepository viaCepRepository) {
        this.prefs = prefs;
        this.repository = repository;
        this.viaCepRepository = viaCepRepository;
    }

    public void loadData(){
        disposables.add(
                repository.getMyself()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> _myself.setValue(response.body()),
                                throwable -> _myself.setValue(null)
                        )
        );
    }

    public void editData(
            String completeName,
            String email,
            String phone,
            String document,
            String zipCode,
            String address,
            String number
    ){
        ProEditRequest toEdit = new ProEditRequest(
                completeName,
                Mask.unmask(phone),
                email,
                Mask.unmask(document),
                null,
                null,
                Mask.unmask(zipCode),
                address,
                number
        );
        disposables.add(
                repository.editMyself(toEdit)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    if (response.body() != null) {
                                        _edited.setValue(true);
                                        prefs.setName(response.body().getCompleteName());
                                    }
                                },
                                throwable -> _edited.setValue(false)
                        )
        );
    }

    public void tryCep(String cep){
        disposables.add(
                viaCepRepository.buscarEnderecoPorCep(cep)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (viaCepResponse, throwable) ->
                                        _address.postValue(viaCepResponse)
                        )
        );
    }

    public boolean validateFragment(
            String nome,
            String email,
            String telefone,
            String cpfCnpj,
            String cep,
            String endereco,
            String numero,
            String novaSenha,
            String confirmarSenha
    ) {
        return true;
    }
}
