package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CriarServicoViewModel extends ViewModel {

    @Inject
    public CriarServicoViewModel(){

    }

    public boolean validateField(
            String criaServ,
            String descricaoServ,
            String precoServ,
            String tempoServ
    ){
        // Validar nome do serviço
        if (criaServ == null || criaServ.trim().isEmpty()) {
            return false;
        }

        // Validar preço
        if (precoServ == null || precoServ.trim().isEmpty()) {
            return false;
        }

        try {
            // Tentar converter para double e verificar se é maior que 0
            double valor = Double.parseDouble(precoServ.replace(",", "."));
            if (valor <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        // Validar tempo
        if (tempoServ == null || tempoServ.trim().isEmpty()) {
            return false;
        }

        try {
            // Tentar converter para inteiro e verificar se é maior que 0
            int tempo = Integer.parseInt(tempoServ);
            if (tempo <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
