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
        // 🔎 Nome
        if (clienteRequest.getNome() == null || clienteRequest.getNome().trim().isEmpty()) {
            throw new Exception("O nome não pode ser nulo ou vazio.");
        }

        // 🔎 Email
        if (clienteRequest.getEmail() == null || clienteRequest.getEmail().trim().isEmpty()) {
            throw new Exception("O e-mail não pode ser nulo ou vazio.");
        }
        if (!clienteRequest.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("Formato de e-mail inválido.");
        }

        // 🔎 CPF/CNPJ
        if (clienteRequest.getCpf_cnpj() == null || clienteRequest.getCpf_cnpj().trim().isEmpty()) {
            throw new Exception("O CPF/CNPJ não pode ser nulo ou vazio.");
        }
        if (!clienteRequest.getCpf_cnpj().matches("\\d{11}|\\d{14}")) {
            throw new Exception("CPF deve ter 11 dígitos ou CNPJ deve ter 14 dígitos numéricos.");
        }

        // 🔎 Senha
        if (clienteRequest.getSenha() == null || clienteRequest.getSenha().trim().isEmpty()) {
            throw new Exception("A senha não pode ser nula ou vazia.");
        }
        if (clienteRequest.getSenha().length() < 6) {
            throw new Exception("A senha deve ter pelo menos 6 caracteres.");
        }

        // 🔎 Telefone
        if (clienteRequest.getTelefone() == null || clienteRequest.getTelefone().trim().isEmpty()) {
            throw new Exception("O telefone não pode ser nulo ou vazio.");
        }
        if (!clienteRequest.getTelefone().matches("\\d{10,11}")) {
            throw new Exception("Telefone deve ter 10 ou 11 dígitos numéricos.");
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
