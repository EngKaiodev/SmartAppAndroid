package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ReviewSimpleResponse;
import com.example.smartapp.databinding.AvaliarCategoryBinding;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AvalicaoAdapter extends  RecyclerView.Adapter<AvalicaoAdapter.ViewHolder>{

    private final List<ReviewSimpleResponse> avList;

    public AvalicaoAdapter(List<ReviewSimpleResponse> avList){this.avList = avList;}

    @NonNull
    @Override
    public AvalicaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AvaliarCategoryBinding binding = AvaliarCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewSimpleResponse avitem = avList.get(position);
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

        public void bind(ReviewSimpleResponse avaliar){
            binding.avalianome.setText(avaliar.getCompleteName());
            binding.avaliacomentario.setText(avaliar.getComment());

            // Converte para LocalDate
            LocalDate dataRegistro = Instant.ofEpochMilli(Long.parseLong(avaliar.getDate()))
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // Data de hoje
            LocalDate hoje = LocalDate.now();

            // Diferença em dias
            long dias = ChronoUnit.DAYS.between(dataRegistro, hoje);

            binding.avaliadata.setText("há " + dias + " dias.");
        }
    }
}
