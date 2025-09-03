package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastrarClienteViewModel extends ViewModel {

    @Inject
    public CadastrarClienteViewModel(){

    }

    public Boolean validatedField(
        String nome,
        String email,
        String telefone,
        String documento,
        String Cep,
        String endereco,
        String Numero,
        String password,
        String verifyPassword
    ){


        if (password != verifyPassword){
            return false;
        }

        return true;
    }
}
