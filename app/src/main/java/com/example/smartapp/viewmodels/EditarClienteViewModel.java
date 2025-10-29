package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditarClienteViewModel extends ViewModel {

    // Padrões de validação
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    );
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}$"
    );
    private static final Pattern CPF_PATTERN = Pattern.compile(
            "^[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}$"
    );
    private static final Pattern CNPJ_PATTERN = Pattern.compile(
            "^[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}$"
    );
    private static final Pattern CEP_PATTERN = Pattern.compile(
            "^[0-9]{5}-?[0-9]{3}$"
    );

    @Inject
    public EditarClienteViewModel(){


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

        // Validar endereço
        if (endereco == null || endereco.trim().isEmpty()) {
            return false;
        }

        // Validar número
        if (numero == null || numero.trim().isEmpty()) {
            return false;
        }

        // Validar senhas (se preenchidas)
        if (!novaSenha.isEmpty() || !confirmarSenha.isEmpty()) {
            if (novaSenha.length() < 6) {
                return false;
            }

            if (!novaSenha.equals(confirmarSenha)) {
                return false;
            }
        }

        return true;
    }
}
