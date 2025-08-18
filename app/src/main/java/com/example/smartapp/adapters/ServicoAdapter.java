package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.databinding.ServicoCategoryBinding;
import com.example.smartapp.domain.models.ServicoModel;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ViewHolder> {

    private final List<ServicoModel> sList;

    public ServicoAdapter(List<ServicoModel> sList){this.sList = sList;}

    @NonNull
    @Override
    public ServicoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ServicoCategoryBinding binding = ServicoCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoAdapter.ViewHolder holder, int position) {
        ServicoModel pitem = sList.get(position);
        holder.bind(pitem);

    }

    @Override
    public int getItemCount() { return sList != null ? sList.size() : 0;   }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ServicoCategoryBinding binding;

        public ViewHolder(ServicoCategoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ServicoModel servico){
            binding.servicoNome.setText(servico.getName());
            binding.servicovalor.setText(servico.getValor());
            binding.tiposervico.setText(servico.getServico());

        }
    }
}
