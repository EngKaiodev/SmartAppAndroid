package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.core.domain.response.ServiceCompleteResponse;
import com.example.smartapp.databinding.ItemProfissionalListBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class ProfissionalListAdapter extends RecyclerView.Adapter<ProfissionalListAdapter.ViewHolder> {

    private List<ProfissionalResponse> originalList;
    private List<ProfissionalResponse> filteredList;
    private final Consumer<ProfissionalResponse> onClick;

    public ProfissionalListAdapter(List<ProfissionalResponse> originalList, Consumer<ProfissionalResponse> onClick){
        this.originalList = new ArrayList<>(originalList);
        this.filteredList = new ArrayList<>(originalList);
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProfissionalListBinding binding = ItemProfissionalListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfissionalResponse item = filteredList.get(holder.getAdapterPosition());
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> onClick.accept(item));
    }

    @Override
    public int getItemCount() { return  filteredList.size(); }
    public void filter(String filter) {
        filteredList.clear();
        if (filter == null || filter.trim().isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            String lowerQuery = filter.toLowerCase(Locale.ROOT);
            for (ProfissionalResponse s : originalList) {
                if (s.getName().toLowerCase(Locale.ROOT).contains(lowerQuery)) {
                    filteredList.add(s);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemProfissionalListBinding binding;

        public ViewHolder (ItemProfissionalListBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProfissionalResponse pro){
            binding.professionalName.setText(pro.getName());
            binding.professionalRating.setText(String.format("%.2f", pro.getRate()));

            if (pro.getReviewsCount() > 99){
                binding.professionalReviews.setText("(+99 avaliações)");
            } else {
                binding.professionalReviews.setText("(" + pro.getReviewsCount() + " avaliações)");
            }
        }

    }
}
