package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastrarServicoViewModel extends ViewModel {

    @Inject
    public CadastrarServicoViewModel(){

    }

    public boolean validateField(
            String servico,
            String descricao,
            String preco
    ){
        // Validar nome do serviço
        if (servico == null || servico.trim().isEmpty()) {
            return false;
        }

        // Validar preço
        if (preco == null || preco.trim().isEmpty()) {
            return false;
        }

        try {
            // Tentar converter para double e verificar se é maior que 0
            double valor = Double.parseDouble(preco.replace(",", "."));
            if (valor <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
