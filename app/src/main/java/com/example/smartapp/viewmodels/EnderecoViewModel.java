package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.repository.EnderecoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EnderecoViewModel extends ViewModel {

    private final EnderecoRepository enderecoRepository;

    @Inject
    public EnderecoViewModel(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
}
