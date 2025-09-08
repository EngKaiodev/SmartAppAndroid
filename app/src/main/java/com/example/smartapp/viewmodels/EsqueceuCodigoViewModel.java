package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EsqueceuCodigoViewModel extends ViewModel {

    @Inject
    public EsqueceuCodigoViewModel(){

    }
    public boolean validateField(
            String codigo1,
            String codigo2,
            String codigo3,
            String codigo4
    ){

        // Verifica se algum campo está vazio
        if (codigo1.isEmpty() || codigo2.isEmpty() || codigo3.isEmpty() || codigo4.isEmpty()) {
            return false;
        }

        // Verifica se todos os campos são dígitos numéricos
        if (!codigo1.matches("\\d") ||
                !codigo2.matches("\\d") ||
                !codigo3.matches("\\d") ||
                !codigo4.matches("\\d")) {
            return false;
        }


        return true;
    }
}
