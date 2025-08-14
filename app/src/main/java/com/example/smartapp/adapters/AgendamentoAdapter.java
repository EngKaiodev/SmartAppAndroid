package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.databinding.ItemAgendamentoBinding;
import com.example.smartapp.domain.models.AgendamentoModel;

import java.util.List;

public class AgendamentoAdapter extends RecyclerView.Adapter<AgendamentoAdapter.ViewHolder> {

    private final List<AgendamentoModel> aList;
    private java.util.function.Consumer<AgendamentoModel> onAgendarClick; //Criar variavel

    public AgendamentoAdapter(List<AgendamentoModel> aList, java.util.function.Consumer<AgendamentoModel> onAgendarClick) {
        this.aList = aList;
        this.onAgendarClick = onAgendarClick; // Colocar no construtor
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
        AgendamentoModel aitem = aList.get(position);
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

        public void binding(AgendamentoModel agendamento) {
            binding.nome.setText(agendamento.getName());
            binding.servico.setText(agendamento.getServico());
            binding.data.setText(agendamento.getData());
            binding.hora.setText(agendamento.getHora());
            binding.valor.setText(agendamento.getValor());

        }
    }

    @Override
    public int getItemCount() {
        return aList != null ? aList.size() : 0;
    }
}
