package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.HistoricResponse;
import com.example.smartapp.databinding.HistoricoCategoryBinding;
import com.example.smartapp.domain.models.HistoricoModel;

import java.util.List;
import java.util.function.Consumer;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private final List<HistoricResponse> hList;
    private Consumer<HistoricResponse> onHistoryClick;

    public HistoricoAdapter(List<HistoricResponse> hList, Consumer<HistoricResponse> onHistoryClick){
        this.hList = hList;
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
        HistoricResponse hitem = hList.get(position);
        holder.bind(hitem);

        holder.itemView.setOnClickListener(view -> onHistoryClick.accept(hitem));

    }

    @Override
    public int getItemCount() {return hList != null ? hList.size(): 0; }

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
