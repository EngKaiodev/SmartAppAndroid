package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastrarClienteViewModel extends ViewModel {

    // Padrões de regex para validação
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^[+]?[0-9]{10,13}$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^[0-9]{8}$");
    private static final Pattern NUMERO_PATTERN = Pattern.compile("^[0-9]+$");

    @Inject
    public CadastrarClienteViewModel(){

    }

    public Boolean validatedField(
        String nome,
        String email,
        String telefone,
        String Cep,
        String endereco,
        String Numero,
        String password,
        String verifyPassword

    ){


        // Validação de Nome
        if (nome == null || nome.trim().isEmpty() || nome.length() < 3) {
            return false;
        }

        // Validação de Email
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return false;
        }

        // Validação de Telefone (mínimo 10 dígitos, máximo 13 com código do país)
        String telLimpo = telefone.replaceAll("[^0-9+]", "");
        if (telLimpo.isEmpty() || !TELEFONE_PATTERN.matcher(telLimpo).matches()) {
            return false;
        }

        // Validação de CEP (apenas 8 dígitos)
        String cepLimpo = Cep.replaceAll("[^0-9]", "");
        if (Cep.isEmpty() || !CEP_PATTERN.matcher(Cep).matches()) {
            return false;
        }

        // Validação de Endereço
        if (endereco == null || endereco.trim().isEmpty() || endereco.length() < 5) {
            return false;
        }

        // Validação de Número
        if (Numero == null || Numero.trim().isEmpty() || !NUMERO_PATTERN.matcher(Numero.trim()).matches()) {
            return false;
        }

        // Validação de Senha
        if (password == null || password.length() < 6 || !password.equals(verifyPassword)) {
            return false;
        }

        return true;
    }
}

