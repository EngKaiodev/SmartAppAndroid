package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.databinding.HistoricoCategoryBinding;
import com.example.smartapp.domain.models.HistoricoModel;

import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private final List<HistoricoModel> hList;

    public HistoricoAdapter(List<HistoricoModel> hList){this.hList = hList;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HistoricoCategoryBinding binding = HistoricoCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoricoModel hitem = hList.get(position);
        holder.bind(hitem);

    }

    @Override
    public int getItemCount() {return 0;}

    static class ViewHolder extends RecyclerView.ViewHolder{
        private HistoricoCategoryBinding binding;

        public ViewHolder(HistoricoCategoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HistoricoModel historico){
            binding.avaliacaoserv.setText(historico.getAvaliacao());
            binding.nomeprof.setText((historico.getName()));
            binding.servicodata.setText(historico.getData());
            binding.nomeservico.setText(historico.getServico());
            binding.servicovalor.setText(historico.getValor());
        }

    }


}
