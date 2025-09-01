package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.request.ClienteRequest;
import com.example.smartapp.core.domain.response.ClienteResponse;
import com.example.smartapp.core.network.service.ClienteService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClienteRepository {

    private final ClienteService clienteService;

    @Inject
    public ClienteRepository(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<ClienteResponse> getAll(){
        return clienteService.getAll();
    }

    public ClienteResponse getById(int id) throws Exception {
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }

        return clienteService.getById(id);
    }

    public ClienteResponse insert(ClienteRequest clienteRequest) throws Exception {
        if (clienteRequest.getNome() == null){
            throw new Exception("O nome não pode ser nulo");
        }
        return clienteService.insert(clienteRequest);
    }

    public void delete(int id) throws Exception {
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }

        clienteService.delete(id);
    }
}
