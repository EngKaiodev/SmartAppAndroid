package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.domain.repository.ClienteRepository;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditarClienteViewModel extends ViewModel {

    private Preferences prefs;
    private ClienteRepository repository;

    @Inject
    public EditarClienteViewModel(Preferences prefs, ClienteRepository repository) {
        this.prefs = prefs;
        this.repository = repository;
    }

    public void loadData(){

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
    ) {
        return true;
    }
}
