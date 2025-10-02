package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.core.domain.response.ProfissionalDetailResponse;
import com.example.smartapp.domain.repository.ProfissionalRepository;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class EditarProfissionalViewModel extends ViewModel {

    private Preferences preferences;
    private ProfissionalRepository repository;

    @Inject
    public EditarProfissionalViewModel (Preferences preferences, ProfissionalRepository repository){
        this.preferences = preferences;
        this.repository = repository;
    }

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<MyselfResponse> _myself = new MutableLiveData<>();
    public LiveData<MyselfResponse> myself = _myself;

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


    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    );
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}$"
    );
    public boolean validateField(
            String nome,
            String email,
            String telefone,
            String cpfCnpj,
            String cep,
            String endereco,
            String numero,
            String razaoSocial,
            String descricao,
            String novaSenha,
            String confirmaSenha

    ){
        // Validar nome
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }

        // Validar email
        if (email == null || email.trim().isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        // Validar telefone
        String phoneDigits = telefone.replaceAll("[^0-9]", "");
        if (phoneDigits.length() < 10 || phoneDigits.length() > 11) {
            return false;
        }

        // Validar CPF/CNPJ (apenas verificar se tem 11 ou 14 dígitos)
        String cpfCnpjDigits = cpfCnpj.replaceAll("[^0-9]", "");
        if (cpfCnpjDigits.length() != 11 && cpfCnpjDigits.length() != 14) {
            return false;
        }

        // Validar CEP
        String cepDigits = cep.replaceAll("[^0-9]", "");
        if (cepDigits.length() != 8) {
            return false;
        }

        // Validar endereço
        if (endereco == null || endereco.trim().isEmpty()) {
            return false;
        }

        // Validar número
        if (numero == null || numero.trim().isEmpty()) {
            return false;
        }

        // Validar senhas (se preenchidas)
        if (!novaSenha.isEmpty() || !confirmaSenha.isEmpty()) {
            if (novaSenha.length() < 6) {
                return false;
            }

            if (!novaSenha.equals(confirmaSenha)) {
                return false;
            }
        }

        return true;
    }
}
