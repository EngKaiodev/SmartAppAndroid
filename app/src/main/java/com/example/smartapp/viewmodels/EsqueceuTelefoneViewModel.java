package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EsqueceuTelefoneViewModel extends ViewModel {

    // Padrões de validação
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\([0-9]{2}\\) [0-9]{5}-[0-9]{4}$" // Formato brasileiro: (11) 99999-9999
    );


    @Inject
    public EsqueceuTelefoneViewModel(){

    }

    public boolean validateField(
            String telefoneemail
    ){
        if (telefoneemail == null || telefoneemail.trim().isEmpty()) {
            return false;
        }

        String input = telefoneemail.trim();

        // Verifica se é um email válido
        if (EMAIL_PATTERN.matcher(input).matches()) {
            return true;
        }

        // Verifica se é um telefone válido (aceita com ou sem máscara)
        String digitsOnly = input.replaceAll("[^0-9]", "");

        // Telefone brasileiro: 10 ou 11 dígitos (com DDD)
        if (digitsOnly.length() == 10 || digitsOnly.length() == 11) {
            return true;
        }

        return false;
    }

    // Método adicional para obter mensagens de erro específicas
    public String getErrorMessage(String telefoneEmail) {
        if (telefoneEmail == null || telefoneEmail.trim().isEmpty()) {
            return "Por favor, informe seu telefone ou e-mail";
        }

        String input = telefoneEmail.trim();
        String digitsOnly = input.replaceAll("[^0-9]", "");

        if (input.contains("@") && !EMAIL_PATTERN.matcher(input).matches()) {
            return "Por favor, informe um e-mail válido";
        }

        if (digitsOnly.length() < 10) {
            return "Por favor, informe um telefone válido com DDD";
        }

        if (digitsOnly.length() > 11) {
            return "Telefone muito longo. Verifique o número";
        }

        return "Por favor, informe um telefone ou e-mail válido";

    }
}
