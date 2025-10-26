package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.response.ServiceCompleteResponse;
import com.example.smartapp.databinding.ItemServiceListBinding;

import java.util.List;
import java.util.function.Consumer;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

    private List<ServiceCompleteResponse> mList;
    private Consumer<ServiceCompleteResponse> onClick;

    public ServiceListAdapter(List<ServiceCompleteResponse> mList, Consumer<ServiceCompleteResponse> onClick) {
        this.mList = mList;
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
        ServiceCompleteResponse item = mList.get(holder.getAdapterPosition());
        holder.itemView.setOnClickListener(v -> onClick.accept(item));
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
