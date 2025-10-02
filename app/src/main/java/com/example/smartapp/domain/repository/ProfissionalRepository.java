package com.example.smartapp.domain.repository;

import android.util.Log;

import com.example.smartapp.core.domain.request.ProfissionalRequest;
import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.core.domain.response.ProfissionalDetailResponse;
import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.core.network.service.ProfissionalService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ProfissionalRepository {

    private final ProfissionalService profissionalService;

    @Inject
    public ProfissionalRepository(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    public Single<Response<MyselfResponse>> getMyself(){
        return profissionalService.getMyself().subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    Log.i("ProfissionalRepository", "Informação recuperada com sucesso, com " + response.body());
                }).doOnError(error -> {
                    Log.i("ProfissionalRepository", "Erro ao recuperar informação: " + error.getLocalizedMessage());
                });
    }

    public Single<Response<List<ProfissionalResponse>>> getAll() {
        return profissionalService.getAll().subscribeOn(Schedulers.io()).doOnSuccess(response -> {
            Log.i("ProfissionalRepository", "Lista recuperada com sucesso, com " + response.body());
        }).doOnError(error -> {
            Log.i("ProfissionalRepository", "Erro ao recuperar lista: " + error.getLocalizedMessage());
        });
    }

    public Single<Response<ProfissionalDetailResponse>> getDetailById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("O id deve ser maior que 0");
        }
        return profissionalService.GetDetailById(id).subscribeOn(Schedulers.io()).doOnSuccess(response -> {
            Log.i("ProfissionalRepository", "Detalhes recuperados com sucesso: " + response.body());
        }).doOnError(error -> {
            Log.i("ProfissionalRepository", "Erro ao recuperar detalhes: " + error.getLocalizedMessage());
        });
    }

    public ProfissionalResponse insert(ProfissionalRequest profissionalRequest) throws Exception {
        // Validação se o objeto request é nulo
        if (profissionalRequest == null) {
            throw new IllegalArgumentException("Request não pode ser nulo");
        }

        // Validação do ID (deve ser 0 para insert)
        if (profissionalRequest.getId_profissional() != 0) {
            throw new IllegalArgumentException("ID deve ser 0 para operação de inserção");
        }

        // Validação do CNPJ
        if (profissionalRequest.getCnpj() == null || profissionalRequest.getCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio");
        }
        if (!isValidCNPJ(profissionalRequest.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        // Validação do contato
        if (profissionalRequest.getContato() == null || profissionalRequest.getContato().trim().isEmpty()) {
            throw new IllegalArgumentException("Contato não pode ser nulo ou vazio");
        }

        // Validação do email
        if (profissionalRequest.getEmail() == null || profissionalRequest.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (!isValidEmail(profissionalRequest.getEmail())) {
            throw new IllegalArgumentException("Email com formato inválido");
        }

        // Validação da senha
        if (profissionalRequest.getSenha() == null || profissionalRequest.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        if (profissionalRequest.getSenha().length() < 8) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }

        // Validação da razão social
        if (profissionalRequest.getRazao_social() == null || profissionalRequest.getRazao_social().trim().isEmpty()) {
            throw new IllegalArgumentException("Razão social não pode ser nula ou vazia");
        }

        // Verificar se CNPJ já existe no banco

        // Verificar se email já existe no banco


        return profissionalService.insert(profissionalRequest);

    }


    private boolean isValidCNPJ(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (CNPJ inválido)
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int soma = 0;
            int peso = 5;
            for (int i = 0; i < 12; i++) {
                int digito = Character.getNumericValue(cnpj.charAt(i));
                soma += digito * peso;
                peso = (peso == 2) ? 9 : peso - 1;
            }

            int resto = soma % 11;
            int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;

            // Verifica o primeiro dígito verificador
            if (digitoVerificador1 != Character.getNumericValue(cnpj.charAt(12))) {
                return false;
            }

            // Calcula o segundo dígito verificador
            soma = 0;
            peso = 6;
            for (int i = 0; i < 13; i++) {
                int digito = Character.getNumericValue(cnpj.charAt(i));
                soma += digito * peso;
                peso = (peso == 2) ? 9 : peso - 1;
            }

            resto = soma % 11;
            int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;

            // Verifica o segundo dígito verificador
            return digitoVerificador2 == Character.getNumericValue(cnpj.charAt(13));

        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método auxiliar para validar email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
