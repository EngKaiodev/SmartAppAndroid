package com.example.smartapp.domain.repository;

import com.example.smartapp.core.network.service.EnderecoService;

import javax.inject.Inject;

public class EnderecoRepository {

    private final EnderecoService enderecoService;

    @Inject
    public EnderecoRepository(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
}
