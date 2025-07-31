package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.databinding.AgendamentoCategoryBinding;
import com.example.smartapp.domain.models.AgendamentoModel;

import java.util.List;

public class AgendamentoAdapter extends RecyclerView.Adapter<AgendamentoAdapter.ViewHolder> {

    private final List<AgendamentoModel> aList;

    public AgendamentoAdapter(List<AgendamentoModel> aList){this.aList = aList;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AgendamentoCategoryBinding binding = AgendamentoCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoAdapter.ViewHolder holder, int position) {
        AgendamentoModel aitem = aList.get(position);
        holder.binding(aitem);

    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private AgendamentoCategoryBinding binding;

        public ViewHolder(AgendamentoCategoryBinding binding){
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
        return 0;
    }
}
