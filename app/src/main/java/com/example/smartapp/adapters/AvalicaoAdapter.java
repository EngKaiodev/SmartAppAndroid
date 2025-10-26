package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ReviewSimpleResponse;
import com.example.smartapp.databinding.AvaliarCategoryBinding;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

            long dias = ChronoUnit.DAYS.between(
                    avaliar.getDate().toLocalDate(),
                    LocalDate.now()
            );

            String message = "há " + dias + " dias.";

            if (dias == 0) message = "Hoje";
            else if (dias > 7 && dias < 30){
                int weeks = Math.toIntExact(dias / 7);
                if (weeks == 1) message = "há " + weeks + " semana.";
                else message = "há " + weeks + " semanas.";
            }
            else if (dias > 30 && dias < 365){
                int months = Math.toIntExact(dias / 30);
                if (months == 1) message = "há " + months + " mês.";
                else message = "há " + months + " meses.";
            }
            else if (dias > 365){
                int years = Math.toIntExact(dias / 365);
                if (years == 1) message = "há " + years + " ano.";
                else message = "há " + years + " anos.";
            }

            binding.avaliadata.setText(message);

            ImageView[] stars = new ImageView[]{
                binding.avaliaEstrela1,
                binding.avaliaEstrela2,
                binding.avaliaEstrela3,
                binding.avaliaEstrela4,
                binding.avaliaEstrela5
            };

            for (int i = 0; i < avaliar.getReview(); i++){
                ImageView start = stars[i];
                start.setVisibility(ViewGroup.VISIBLE);
            }
        }
    }
}
