package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.databinding.ItemProfissionalListBinding;

import java.util.List;
import java.util.function.Consumer;

public class ProfissionalListAdapter extends RecyclerView.Adapter<ProfissionalListAdapter.ViewHolder> {

    private List<ProfissionalResponse> mList;
    private final Consumer<ProfissionalResponse> onClick;

    public ProfissionalListAdapter(List<ProfissionalResponse> mList, Consumer<ProfissionalResponse> onClick){
        this.mList = mList;
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
        ProfissionalResponse item = mList.get(holder.getAdapterPosition());
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> onClick.accept(item));
    }

    @Override
    public int getItemCount() { return  mList.size(); }

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
