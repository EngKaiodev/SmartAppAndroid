package com.example.smartapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
    public CadastrarClienteViewModel(ViaCepRepository viaCepRepository, AuthRepository authRepository){
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
    ){
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

        String cpfCnpjLimpo = CpfCnpj.replaceAll("[^0-9]", "");
        if (cpfCnpjLimpo.length() == 11) {
            if (!validarCPF(cpfCnpjLimpo)) return false;
        } else if (cpfCnpjLimpo.length() == 14) {
            if (!validarCNPJ(cpfCnpjLimpo)) return false;
        } else {
            return false;
        }

        return true;
    }

    private boolean validarCPF(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) primeiroDigito = 0;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) segundoDigito = 0;

        return (primeiroDigito == Character.getNumericValue(cpf.charAt(9)) &&
                segundoDigito == Character.getNumericValue(cpf.charAt(10)));
    }

    private boolean validarCNPJ(String cnpj) {
        if (cnpj == null || !CNPJ_PATTERN.matcher(cnpj).matches()) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (CNPJ inválido)
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Cálculo do primeiro dígito verificador
        int soma = 0;
        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
        }
        int primeiroDigito = soma % 11;
        primeiroDigito = (primeiroDigito < 2) ? 0 : 11 - primeiroDigito;

        // Cálculo do segundo dígito verificador
        soma = 0;
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
        }
        int segundoDigito = soma % 11;
        segundoDigito = (segundoDigito < 2) ? 0 : 11 - segundoDigito;

        // Verifica se os dígitos calculados coincidem com os informados
        return (primeiroDigito == Character.getNumericValue(cnpj.charAt(12)) &&
                segundoDigito == Character.getNumericValue(cnpj.charAt(13)));
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
            Integer type
    ) {
        return authRepository.register(
                new UserRequest(
                        completeName,
                        password,
                        phone,
                        email,
                        document,
                        type
                )
        );
    }

}