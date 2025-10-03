package com.example.smartapp.viewmodels;

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
import retrofit2.Response;

@HiltViewModel
public class CadastrarProfissionalViewModel extends ViewModel {

    private final ViaCepRepository viaCepRepository;
    private final AuthRepository authRepository;

    // Padrões de regex para validação
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^[+]?[0-9]{10,13}$");
    private static final Pattern CPF_PATTERN = Pattern.compile("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}-[0-9]{2}$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");
    private static final Pattern SENHA_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    @Inject
    public CadastrarProfissionalViewModel(ViaCepRepository viaCepRepository, AuthRepository authRepository){
        this.viaCepRepository = viaCepRepository;
        this.authRepository = authRepository;
    }

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
            String password,
            String verifyPassword

    ){

        // Validação de Nome
        if (nome == null || nome.trim().isEmpty() || nome.trim().length() < 3) {
            return false;
        }

        // Validação de Email
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return false;
        }

        // Validação de Telefone (limpa caracteres não numéricos, permite + no começo)
        String telLimpo = (telefone == null) ? "" : telefone.replaceAll("[^0-9+]", "");
        if (telLimpo.isEmpty() || !TELEFONE_PATTERN.matcher(telLimpo).matches()) {
            return false;
        }

        // Validação de CPF/CNPJ (aceita com ou sem pontuação — limpa tudo e verifica tamanho)
        String docLimpo = (cpfCnpj == null) ? "" : cpfCnpj.replaceAll("[^0-9]", "");
        if (docLimpo.isEmpty()) {
            return false;
        } else if (docLimpo.length() == 11) {
            // CPF (aqui só checamos tamanho; validação dos dígitos verificadores pode ser adicionada)
        } else if (docLimpo.length() == 14) {
            // CNPJ: exige razão social
            if (razaoSocial == null || razaoSocial.trim().isEmpty() || razaoSocial.trim().length() < 3) {
                return false;
            }
        } else {
            return false;
        }

        // Validação de CEP (apenas 8 dígitos)
        String cepLimpo = (cep == null) ? "" : cep.replaceAll("[^0-9]", "");
        if (cepLimpo.isEmpty() || !CEP_PATTERN.matcher(cepLimpo).matches()) {
            return false;
        }

        // Validação de Endereço
        if (endereco == null || endereco.trim().isEmpty() || endereco.trim().length() < 5) {
            return false;
        }

        // Validação de Número (aceita "S/N" ou número)
        if (numero == null || numero.trim().isEmpty()) {
            return false;
        } else {
            String numTrim = numero.trim();
            if (!(numTrim.equalsIgnoreCase("S/N") || numero.matches(numTrim))) {
                return false;
            }
        }

        // Validação de Senha (mínimo 6 caracteres, igual à confirmação)
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
            String altername,
            String description,
            String zipcode,
            String address,
            String number
    ) {
        return authRepository.register(
                new UserRequest(
                        completeName,
                        password,
                        phone,
                        email,
                        document,
                        type,
                        altername,
                        description,
                        zipcode,
                        address,
                        number
                )
        );
    }


}
