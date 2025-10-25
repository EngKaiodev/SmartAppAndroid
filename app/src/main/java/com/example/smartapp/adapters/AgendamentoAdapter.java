package com.example.smartapp.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.databinding.ItemAgendamentoBinding;

import java.util.List;

public class AgendamentoAdapter extends RecyclerView.Adapter<AgendamentoAdapter.ViewHolder> {

    private final List<ScheduleResponse> aList;
    private java.util.function.Consumer<ScheduleResponse> onAgendarClick; //Criar variavel

    public AgendamentoAdapter(List<ScheduleResponse> aList, java.util.function.Consumer<ScheduleResponse> onAgendarClick) {
        this.aList = aList;
        this.onAgendarClick = onAgendarClick;
    }


    @NonNull
    @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAgendamentoBinding binding = ItemAgendamentoBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull AgendamentoAdapter.ViewHolder holder, int position) {
        ScheduleResponse aitem = aList.get(position);
        holder.binding(aitem);

        //Colocar o click no botão aqui
        holder.binding.btnReagendar.setOnClickListener(v -> {
            if (onAgendarClick != null) {
                onAgendarClick.accept(aitem);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemAgendamentoBinding binding;

        public ViewHolder(ItemAgendamentoBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void binding(ScheduleResponse agendamento) {
            binding.nome.setText(agendamento.getProfissional().getName());
            binding.servico.setText(agendamento.getServico().getName());
            binding.data.setText(agendamento.getHour().getString());
            binding.valor.setText(String.format("Valor: R$ %.2f", agendamento.getServico().getPrice()));
            binding.hora.setText(agendamento.getHour().toString());
        }
    }

    @Override
    public int getItemCount() {
        return aList != null ? aList.size() : 0;
    }
}
