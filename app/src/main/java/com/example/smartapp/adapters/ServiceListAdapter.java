package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ServiceCompleteResponse;
import com.example.smartapp.databinding.ItemServiceListBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

    private List<ServiceCompleteResponse> originalList;
    private List<ServiceCompleteResponse> filteredList;
    private Consumer<ServiceCompleteResponse> onClick;

    public ServiceListAdapter(List<ServiceCompleteResponse> originalList, Consumer<ServiceCompleteResponse> onClick) {
        this.originalList = new ArrayList<>(originalList);
        this.filteredList = new ArrayList<>(originalList);
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemServiceListBinding binding = ItemServiceListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceCompleteResponse item = filteredList.get(holder.getAdapterPosition());
        holder.itemView.setOnClickListener(v -> onClick.accept(item));
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String filter) {
        filteredList.clear();
        if (filter == null || filter.trim().isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            String lowerQuery = filter.toLowerCase(Locale.ROOT);
            for (ServiceCompleteResponse s : originalList) {
                if (s.getServiceName().toLowerCase(Locale.ROOT).contains(lowerQuery)) {
                    filteredList.add(s);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemServiceListBinding binding;

        public ViewHolder(ItemServiceListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ServiceCompleteResponse item){
            binding.nomeprof.setText("Com: " + item.getProfissionalName());
            binding.nomeservico.setText(item.getServiceName());
            binding.servicovalor.setText(String.format("Valor: R$ %.2f", item.getServicePrice()));
        }
    }
}
