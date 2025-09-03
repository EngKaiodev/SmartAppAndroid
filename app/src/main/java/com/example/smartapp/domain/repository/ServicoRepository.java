package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.core.network.service.ServicoService;

import java.util.List;

import javax.inject.Inject;

public class ServicoRepository {

    private final ServicoService servicoService;

    @Inject
    public ServicoRepository(ServicoService servicoService){
        this.servicoService = servicoService;
    }

    public List<ServicoResponse> getAll(){return servicoService.getAll();}

    public ServicoResponse getById(int id) throws Exception{
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }
        return servicoService.getById(id);
    }

    public ServicoResponse insert(ServicoRequest servicoRequest) throws Exception{
        // Validação se o objeto request é nulo
        if (servicoRequest == null) {
            throw new IllegalArgumentException("Request não pode ser nulo");
        }

        // Validação do nome (getNone() - presumindo que seja o nome)
        if (servicoRequest.getNome() == null || servicoRequest.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (servicoRequest.getNome().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }

        // Validação da descrição
        if (servicoRequest.getDescricacao() == null || servicoRequest.getDescricacao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }
        if (servicoRequest.getDescricacao().length() < 10) {
            throw new IllegalArgumentException("Descrição deve ter pelo menos 10 caracteres");
        }

        // Validação da duração
        if (servicoRequest.getDuracao() <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que 0");
        }
        if (servicoRequest.getDuracao() > 480) { // 8 horas em minutos
            throw new IllegalArgumentException("Duração não pode exceder 8 horas (480 minutos)");
        }

        // Validação do preço
        if (servicoRequest.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que 0");
        }
        if (servicoRequest.getPreco() > 10000) {
            throw new IllegalArgumentException("Preço não pode exceder R$ 10.000,00");
        }

        // Validação do ID do profissional
        if (servicoRequest.getId_profissional() <= 0) {
            throw new IllegalArgumentException("ID do profissional deve ser maior que 0");
        }
        return servicoService.insert(servicoRequest);
    }

    public void delete(int id) throws Exception{
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }
        servicoService.delete(id);
    }
}
