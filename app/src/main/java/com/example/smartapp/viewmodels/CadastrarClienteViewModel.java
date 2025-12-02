package com.example.smartapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Mask;
import com.example.smartapp.core.domain.request.UserRequest;
import com.example.smartapp.core.domain.response.UserResponse;
import com.example.smartapp.core.domain.response.ViaCepResponse;
import com.example.smartapp.domain.repository.AuthRepository;
import com.example.smartapp.domain.repository.ViaCepRepository;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@HiltViewModel
public class CadastrarClienteViewModel extends ViewModel {

    private final ViaCepRepository viaCepRepository;
    private final AuthRepository authRepository;


    // Padrões de regex para validação
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^[+]?[0-9]{10,13}$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");
    private static final Pattern NUMERO_PATTERN = Pattern.compile("^[0-9]+$");
    private static final Pattern CPF_PATTERN = Pattern.compile("^[0-9]{11}$");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("^[0-9]{14}$");

    @Inject
    public CadastrarClienteViewModel(ViaCepRepository viaCepRepository, AuthRepository authRepository) {
        this.viaCepRepository = viaCepRepository;
        this.authRepository = authRepository;
    }

    public Boolean validatedField(
            String nome,
            String email,
            String telefone,
            String Cep,
            String endereco,
            String Numero,
            String password,
            String verifyPassword,
            String CpfCnpj
    ) {
        if (nome == null || nome.isBlank() || nome.length() < 3) {
            return false;
        }

        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return false;
        }

        String telLimpo = telefone.replaceAll("[^0-9+]", "");
        if (telLimpo.isEmpty() || !TELEFONE_PATTERN.matcher(telLimpo).matches()) {
            return false;
        }

        String cepLimpo = Cep.replaceAll("[^0-9]", "");
        if (cepLimpo.isEmpty() || !CEP_PATTERN.matcher(cepLimpo).matches()) {
            return false;
        }

        if (endereco == null || endereco.isBlank() || endereco.length() < 5) {
            return false;
        }

        if (Numero == null || Numero.isBlank() || !NUMERO_PATTERN.matcher(Numero.trim()).matches()) {
            return false;
        }

        if (password == null || password.length() < 6 || !password.equals(verifyPassword)) {
            return false;
        }


        return true;
    }

    public Single<ViaCepResponse> buscarCep(String cep) {
        return viaCepRepository.buscarEnderecoPorCep(cep);
    }

    public Single<Response<UserResponse>> register(
            String completeName,
            String password,
            String phone,
            String email,
            String document,
            Integer type,
            String zipcode,
            String address,
            String number,
            String token
    ) {
        return authRepository.register(
                new UserRequest(
                        completeName,
                        password,
                        Mask.unmask(phone),
                        email,
                        Mask.unmask(document),
                        type,
                        Mask.unmask(zipcode),
                        address,
                        number,
                        token
                )
        );
    }

}