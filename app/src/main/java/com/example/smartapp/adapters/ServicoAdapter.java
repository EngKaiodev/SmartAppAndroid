package com.example.smartapp.adapters;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.databinding.ServicoCategoryBinding;
import com.example.smartapp.domain.models.ServicoModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ViewHolder> {

    private final List<ServicoResponse> sList;
    private final Consumer<ServicoResponse> onEditClick;
    private final Consumer<ServicoResponse> onDeleteClick;
    private final boolean isEditing;

    public ServicoAdapter(List<ServicoResponse> sList, Consumer<ServicoResponse> onEditClick, Consumer<ServicoResponse> onDeleteClick, boolean isEditing) {
        this.sList = sList;
        this.onEditClick = onEditClick;
        this.onDeleteClick = onDeleteClick;
        this.isEditing = isEditing;
    }

    @NonNull
    @Override
    public ServicoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ServicoCategoryBinding binding = ServicoCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoAdapter.ViewHolder holder, int position) {
        ServicoResponse pitem = sList.get(position);
        holder.bind(pitem, isEditing);
        holder.binding.editarservico.setOnClickListener(v -> onEditClick.accept(pitem));
        holder.binding.excluirservico.setOnClickListener(v -> onDeleteClick.accept(pitem));
    }

    @Override
    public int getItemCount() { return sList != null ? sList.size() : 0;   }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ServicoCategoryBinding binding;

        public ViewHolder(ServicoCategoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ServicoResponse servico, boolean isEditing){
            binding.servicoNome.setText(servico.getName());

            NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String precoFormatado = formato.format(servico.getPrice());

            binding.servicovalor.setText(precoFormatado);
            binding.tiposervico.setText(servico.getDescription());

            if (isEditing){
                binding.actionButtons.setVisibility(VISIBLE);
            } else {
                binding.actionButtons.setVisibility(INVISIBLE);
            }
        }
    }
}
