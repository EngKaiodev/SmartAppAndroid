package com.example.smartapp.core.domain.response;

import com.example.smartapp.R;

import java.io.Serializable;

public class ScheduleResponse implements Serializable {
    private int id;
    private ProfissionalResponse profissional;
    private ServicoResponse servico;
    private HourResponse hour;

    private int status; // 0 - aguardando confirmação 1 - confirmado 2 - cancelado 3 - Realizado

    public ScheduleResponse() {
    }

    public ScheduleResponse(int id, ProfissionalResponse profissional, ServicoResponse servico, HourResponse hour, int status) {
        this.id = id;
        this.profissional = profissional;
        this.servico = servico;
        this.hour = hour;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfissionalResponse getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalResponse profissional) {
        this.profissional = profissional;
    }

    public ServicoResponse getServico() {
        return servico;
    }

    public void setServico(ServicoResponse servico) {
        this.servico = servico;
    }

    public HourResponse getHour() {
        return hour;
    }

    public void setHour(HourResponse hour) {
        this.hour = hour;
    }

    public StatusInfo getStatusInfo() {
        switch (status) {
            case 0:
                return new StatusInfo("Aguardando Confirmação",
                        0xFFFFC107,
                        R.drawable.ic_warning);
            case 1:
                return new StatusInfo("Confirmado",
                        0xFF4CAF50,
                        R.drawable.ic_check_circle);
            case 2:
                return new StatusInfo("Cancelado",
                        0xFFF44336,
                        R.drawable.ic_close);
            case 3:
                return new StatusInfo("Realizado",
                        0xFF4F46E5,
                        R.drawable.ic_briefcase);
            default:
                return new StatusInfo("Desconhecido",
                        0xFF9E9E9E,
                        R.drawable.ic_settings);
        }
    }

    public static class StatusInfo {
        private final String label;
        private final int color;
        private final int iconRes;

        public StatusInfo(String label, int color, int iconRes) {
            this.label = label;
            this.color = color;
            this.iconRes = iconRes;
        }

        public String getLabel() {
            return label;
        }

        public int getColor() {
            return color;
        }

        public int getIconRes() {
            return iconRes;
        }
    }
}
