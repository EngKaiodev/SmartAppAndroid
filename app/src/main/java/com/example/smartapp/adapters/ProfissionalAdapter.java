package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.databinding.ProfissionalCategoryBinding;
import com.example.smartapp.domain.models.ProfissionalModel;


import java.util.List;
import java.util.function.Consumer;

public class ProfissionalAdapter extends RecyclerView.Adapter<ProfissionalAdapter.ViewHolder>{

    private final List<ProfissionalModel> pList;
    private Consumer<ProfissionalModel> onProfissionalClick;

    public ProfissionalAdapter(List<ProfissionalModel> pList, Consumer<ProfissionalModel> onProfissionalClick){
        this.pList = pList;
        this.onProfissionalClick = onProfissionalClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProfissionalCategoryBinding binding = ProfissionalCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfissionalModel pitem = pList.get(position);
        holder.bind(pitem);

        holder.binding.btnirparaagendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfissionalClick.accept(pitem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pList != null ? pList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ProfissionalCategoryBinding binding;

        public ViewHolder(ProfissionalCategoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProfissionalModel profissional){
            binding.avaliacao.setText(profissional.getAvaliacao());
            binding.especialidade.setText(profissional.getEspecialidade());
            binding.nome.setText(profissional.getName());
        }


    }



}
