package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.HistoricResponse;
import com.example.smartapp.databinding.HistoricoCategoryBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private final List<HistoricResponse> originalList;
    private final List<HistoricResponse> filteredList;
    private int type;

    public HistoricoAdapter(int type, List<HistoricResponse> originalList) {
        this.originalList = new ArrayList<>(originalList);
        this.filteredList = new ArrayList<>(originalList);
        this.type = type;
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
        holder.bind(hitem, type);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String nome, String servico) {
        filteredList.clear();

        if (nome != null && !nome.trim().isEmpty()) {
            String lowerQuery = nome.toLowerCase(Locale.ROOT);
            for (HistoricResponse s : originalList) {
                if (s.getProfissional().getName().toLowerCase(Locale.ROOT).contains(lowerQuery)) {
                    filteredList.add(s);
                }
            }
        } else {
            filteredList.addAll(originalList);
        }

        if (servico != null && !servico.trim().isEmpty()) {
            String lowerQuery = servico.toLowerCase(Locale.ROOT);
            filteredList.removeIf(s -> !s.getServico().getName().toLowerCase(Locale.ROOT).contains(lowerQuery));
        }

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private HistoricoCategoryBinding binding;

        public ViewHolder(HistoricoCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HistoricResponse historico, int type) {
            setRatingStars(historico.getRate());

            if (type == 1) {
                binding.nomeprof.setText(historico.getCliente().getCompleteName());
            } else {
                binding.nomeprof.setText((historico.getProfissional().getName()));
            }

            binding.servicodata.setText(historico.getHour().getString());
            binding.nomeservico.setText(historico.getServico().getName());
            binding.servicovalor.setText(String.format("Valor: R$ %.2f", historico.getServico().getPrice()));
        }

        private void setRatingStars(float rate) {
            ImageView[] stars = new ImageView[]{
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
