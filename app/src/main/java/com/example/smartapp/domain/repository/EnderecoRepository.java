package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.request.EnderecoRequest;
import com.example.smartapp.core.domain.response.EnderecoResponse;
import com.example.smartapp.core.network.service.EnderecoService;

import java.util.List;

import javax.inject.Inject;

public class EnderecoRepository {

    private final EnderecoService enderecoService;

    @Inject
    public EnderecoRepository(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    public List<EnderecoResponse> getAll(){ return enderecoService.getAll();}

    public EnderecoResponse getById(int id) throws Exception{
        if (id <= 0 ) {
            throw new Exception("O id deve ser maior que 0");
        }
        return enderecoService.getById(id);
    }

    public EnderecoResponse insert(EnderecoRequest enderecoRequest) throws Exception{
        if (enderecoRequest.getCep() == null || enderecoRequest.getCep().isEmpty()) {
            throw new Exception("O cep é obrigatório");
        }
        if (enderecoRequest.getRua() == null || enderecoRequest.getRua().isEmpty()) {
            throw new Exception("A rua é obrigatória");
        }
        if (enderecoRequest.getNumero() == null || enderecoRequest.getNumero().isEmpty()) {
            throw new Exception("O número é obrigatório");
        }
        if (enderecoRequest.getBairro() == null || enderecoRequest.getBairro().isEmpty()) {
            throw new Exception("O bairro é obrigatório");
        }
        if (enderecoRequest.getCidade() == null || enderecoRequest.getCidade().isEmpty()) {
            throw new Exception("A cidade é obrigatória");
        }
        if (enderecoRequest.getComplemento() == null || enderecoRequest.getComplemento().isEmpty()) {
            throw new Exception("O complemento é obrigatório");
        }
        if(enderecoRequest.getPais() == null || enderecoRequest.getPais().isEmpty()){
            throw new Exception("O país é obrigatório");
        }
        return enderecoService.insert(enderecoRequest);

    }
    public void delete(int id) throws Exception{
        if (id <= 0 ) {
            throw new Exception("O id deve ser maior que 0");
        }
        enderecoService.delete(id);

    }
}
