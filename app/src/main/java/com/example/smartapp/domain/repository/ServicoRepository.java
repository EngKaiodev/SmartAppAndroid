package com.example.smartapp.domain.repository;

import com.example.smartapp.core.network.service.ServicoService;

import javax.inject.Inject;

public class ServicoRepository {

    private final ServicoService servicoService;

    @Inject
    public ServicoRepository(ServicoService servicoService){
        this.servicoService = servicoService;
    }
}
