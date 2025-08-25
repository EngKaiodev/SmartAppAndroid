package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.databinding.AvaliarCategoryBinding;
import com.example.smartapp.databinding.AvaliarTelaBinding;
import com.example.smartapp.domain.models.AvaliacaoModel;

import java.util.List;

public class AvalicaoAdapter extends  RecyclerView.Adapter<AvalicaoAdapter.ViewHolder>{

    private final List<AvaliacaoModel> avList;

    public AvalicaoAdapter(List<AvaliacaoModel> avList){this.avList = avList;}

    @NonNull
    @Override
    public AvalicaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AvaliarCategoryBinding binding = AvaliarCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AvaliacaoModel avitem = avList.get(position);
        holder.bind(avitem);

    }

    @Override
    public int getItemCount() {
        return  avList != null ? avList.size() :0;    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        private AvaliarCategoryBinding binding;

        public ViewHolder(AvaliarCategoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(AvaliacaoModel avaliar){
            binding.avalianome.setText(avaliar.getNome());
            binding.avaliacomentario.setText(avaliar.getComentario());
            binding.avaliadata.setText((avaliar.getData()));


        }
    }
}
