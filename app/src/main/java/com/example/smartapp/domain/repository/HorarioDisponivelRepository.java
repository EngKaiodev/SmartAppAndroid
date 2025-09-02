package com.example.smartapp.domain.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.smartapp.core.domain.request.HorarioDisponivelRequest;
import com.example.smartapp.core.domain.response.HorarioDisponivelResponse;
import com.example.smartapp.core.network.service.HorarioDisponivelService;

import java.util.List;

import javax.inject.Inject;

public class HorarioDisponivelRepository {

    private final HorarioDisponivelService horarioDisponivelService;

    @Inject
    public HorarioDisponivelRepository(HorarioDisponivelService horarioDisponivelService){
        this.horarioDisponivelService = horarioDisponivelService;
    }

    public List<HorarioDisponivelResponse> getAll(){return horarioDisponivelService.getAll();}

    public HorarioDisponivelResponse getById(int id) throws Exception{
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }
        // 🔎 Buscar no banco
        HorarioDisponivelResponse response = horarioDisponivelService.getById(id);
        if (response == null) {
            throw new Exception("Nenhum horário disponível encontrado para o ID: " + id);
        }

        // 🔎 Validação opcional: verificar se o horário está ativo
        if (response.getStatus() != null && response.getStatus().equalsIgnoreCase("INDISPONIVEL")) {
            throw new Exception("O horário informado não está mais disponível.");
        }

        return horarioDisponivelService.getById(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HorarioDisponivelResponse insert(HorarioDisponivelRequest horarioDisponivelRequest) throws Exception{
        // 🔎 Validação Data
        if (horarioDisponivelRequest.getData() == null) {
            throw new Exception("A data do horário não pode ser nula.");
        }
        if (horarioDisponivelRequest.getData().isBefore(java.time.LocalDate.now())) {
            throw new Exception("A data do horário não pode ser anterior à data atual.");
        }

        // 🔎 Validação Horário
        if (horarioDisponivelRequest.getHora_inicio() == null || horarioDisponivelRequest.getHora_fim() == null) {
            throw new Exception("O horário de início e fim devem ser informados.");
        }
        if (horarioDisponivelRequest.getHora_fim().isBefore(horarioDisponivelRequest.getHora_inicio())) {
            throw new Exception("O horário de fim não pode ser anterior ao horário de início.");
        }
        if (horarioDisponivelRequest.getHora_inicio().equals(horarioDisponivelRequest.getHora_fim())) {
            throw new Exception("O horário de início e fim não podem ser iguais.");
        }

        // 🔎 Validação Profissional
        if (horarioDisponivelRequest.getId_profissional() <= 0) {
            throw new Exception("O ID do profissional deve ser maior que 0.");
        }

        // 🔎 Validação Status
        if (horarioDisponivelRequest.getStatus() == null || horarioDisponivelRequest.getStatus().trim().isEmpty()) {
            throw new Exception("O status do horário deve ser informado.");
        }
        List<String> statusValidos = List.of("DISPONIVEL", "INDISPONIVEL");
        if (!statusValidos.contains(horarioDisponivelRequest.getStatus().toUpperCase())) {
            throw new Exception("Status inválido. Valores aceitos: DISPONIVEL ou INDISPONIVEL.");
        }

        return horarioDisponivelService.insert(horarioDisponivelRequest);
    }

    public void delete(int id) throws Exception{
        if (id <= 0 ){
            throw new Exception("O id deve ser maior que 0");
        }

        // 🔎 Verificar se o registro existe no banco

        // 🔎 Validação de status (se aplicável, exemplo: não excluir registros ativos/finalizados)

    }
}
