package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.HistoricResponse;
import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.databinding.HistoricoCategoryBinding;
import com.example.smartapp.domain.models.HistoricoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private final List<HistoricResponse> originalList;
    private final List<HistoricResponse> filteredList;
    private Consumer<HistoricResponse> onHistoryClick;

    public HistoricoAdapter(List<HistoricResponse> originalList, Consumer<HistoricResponse> onHistoryClick){
        this.originalList = new ArrayList<>(originalList);
        this.filteredList = new ArrayList<>(originalList);
        this.onHistoryClick = onHistoryClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HistoricoCategoryBinding binding = HistoricoCategoryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoricResponse hitem = filteredList.get(position);
        holder.bind(hitem);

        holder.itemView.setOnClickListener(view -> onHistoryClick.accept(hitem));

    }

    @Override
    public int getItemCount() {return filteredList.size(); }

    public void filter(String nome, String servico) {
        filteredList.clear();

        if (nome != null && !nome.trim().isEmpty()){
            String lowerQuery = nome.toLowerCase(Locale.ROOT);
            for (HistoricResponse s : originalList) {
                if (s.getProfissional().getName().toLowerCase(Locale.ROOT).contains(lowerQuery)) {
                    filteredList.add(s);
                }
            }
        } else {
            filteredList.addAll(originalList);
        }

        if (servico != null && !servico.trim().isEmpty()){
            String lowerQuery = servico.toLowerCase(Locale.ROOT);
            filteredList.removeIf(s -> !s.getServico().getName().toLowerCase(Locale.ROOT).contains(lowerQuery));
        }

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private HistoricoCategoryBinding binding;

        public ViewHolder(HistoricoCategoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HistoricResponse historico){
            setRatingStars(historico.getRate());
            binding.nomeprof.setText((historico.getProfissional().getName()));
            binding.servicodata.setText(historico.getHour().getString());
            binding.nomeservico.setText(historico.getServico().getName());
            binding.servicovalor.setText(String.format("Valor: R$ %.2f", historico.getServico().getPrice()));
        }

        private void setRatingStars(float rate) {
            ImageView[] stars = new ImageView[] {
                    binding.star1,
                    binding.star2,
                    binding.star3,
                    binding.star4,
                    binding.star5
            };

            for (int i = 0; i < stars.length; i++) {
                if (rate >= i + 1) {
                    stars[i].setImageResource(R.drawable.ic_star_filled);
                } else {
                    stars[i].setImageResource(R.drawable.ic_star_empty);
                }
            }
        }

    }
}
