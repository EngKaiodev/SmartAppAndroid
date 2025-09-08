package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AvaliarServicoViewModel extends ViewModel {

    @Inject
    public AvaliarServicoViewModel(){

    }

    public boolean validateField(
        String prestador,
        String servicoRealizado,
        String data,
        String comentario
    ){
        // Validar se o prestador foi informado
        if (prestador == null || prestador.trim().isEmpty()) {
            return false;
        }

        // Validar se o serviço realizado foi informado
        if (servicoRealizado == null || servicoRealizado.trim().isEmpty()) {
            return false;
        }

        // Validar se a data foi informada
        if (data == null || data.trim().isEmpty()) {
            return false;
        }

        // O comentário é opcional, então não precisa de validação

        return true;
    }
}
