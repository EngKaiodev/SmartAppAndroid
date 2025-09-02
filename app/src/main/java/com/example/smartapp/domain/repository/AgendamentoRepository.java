package com.example.smartapp.domain.repository;

import com.example.smartapp.core.domain.request.AgendamentoRequest;
import com.example.smartapp.core.domain.response.AgendamentoResponse;
import com.example.smartapp.core.network.service.AgendamentoService;

import java.util.List;

import javax.inject.Inject;

public class AgendamentoRepository {

    private final AgendamentoService agendamentoService;

    @Inject
    public AgendamentoRepository(AgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    public List<AgendamentoResponse> getAll(){return agendamentoService.getAll();}

    public AgendamentoResponse getById(int id) throws Exception{
        if (id <= 0 ) {
            throw new Exception("O id deve ser maior que 0");
        }
        return agendamentoService.getById(id);
    }

    public AgendamentoResponse insert(AgendamentoRequest agendamentoRequest) throws Exception{
        if (agendamentoRequest.getClienteId() <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }

        // 🔎 Validação ServicoId
        if (agendamentoRequest.getServicoId() <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido.");
        }

        // 🔎 Validação HorarioId
        if (agendamentoRequest.getHorarioId() <= 0) {
            throw new IllegalArgumentException("ID do horário inválido.");
        }

        // 🔎 Validação opcional: Comentários (não pode ser muito grande)
        if (agendamentoRequest.getComentarios() != null && agendamentoRequest.getComentarios().length() > 500) {
            throw new IllegalArgumentException("O campo comentários não pode ultrapassar 500 caracteres.");
        }

        // 🔎 Validação opcional: Status (ex: deve estar entre valores conhecidos)
        if (agendamentoRequest.getStatus() != null) {
            List<String> statusValidos = List.of("PENDENTE", "CONFIRMADO", "CANCELADO");
            if (!statusValidos.contains(agendamentoRequest.getStatus().toUpperCase())) {
                throw new IllegalArgumentException("Status inválido. Valores aceitos: PENDENTE, CONFIRMADO, CANCELADO.");
            }
        }

        // 🔎 Validação opcional: Nota (se enviada, deve estar entre 0 e 5)
        if (agendamentoRequest.getNota() < 0 || agendamentoRequest.getNota() > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 5.");
        }

        return agendamentoService.insert(agendamentoRequest);
    }

    public void delete(int id) throws Exception{
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que 0.");
        }

        // 🔎 Verificar se o agendamento existe
        // 🔎 Validar status antes de excluir (exemplo: não excluir se já estiver finalizado)

        agendamentoService.delete(id);
    }

}
