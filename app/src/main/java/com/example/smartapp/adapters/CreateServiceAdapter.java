package com.example.smartapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.databinding.ItemServicosBinding;

import java.util.ArrayList;
import java.util.List;

public class CreateServiceAdapter extends RecyclerView.Adapter<CreateServiceAdapter.ViewHolder> {

    private final List<ServicoRequest> servicos = new ArrayList<>();
    private final int proId;
    private RecyclerView recyclerView;

    public CreateServiceAdapter(int count, int proId) {
        for (int i = 0; i < count; i++) {
            servicos.add(new ServicoRequest());
        }
        this.proId = proId;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public void addCount() {
        servicos.add(new ServicoRequest());
        notifyItemInserted(servicos.size() - 1);
    }

    public void exclude(int position) {
        if (position >= 0 && position < servicos.size()) {
            servicos.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, servicos.size());
        }
    }

    public List<ServicoRequest> getServicosAtualizados() {
        List<ServicoRequest> listaAtualizada = new ArrayList<>();

        for (int i = 0; i < getItemCount(); i++) {
            RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);

            if (holder instanceof ViewHolder viewHolder) {
                String nome = viewHolder.binding.edtNomeServico.getText().toString().trim();
                String descricao = viewHolder.binding.edtDescricaoServico.getText().toString().trim();
                String precoStr = viewHolder.binding.edtPrecoServico.getText().toString().trim();
                String durationStr = viewHolder.binding.edtDuracaoServico.getText().toString().trim();

                if (nome.isEmpty() || descricao.isEmpty() || precoStr.isEmpty() || durationStr.isEmpty()) {
                    continue;
                }

                double preco;
                int duracao;

                try {
                    preco = Double.parseDouble(precoStr);
                } catch (NumberFormatException e) {
                    continue;
                }

                try {
                    duracao = Integer.parseInt(durationStr);
                } catch (NumberFormatException e) {
                    continue;
                }

                ServicoRequest servico = new ServicoRequest();
                servico.setName(nome);
                servico.setDescription(descricao);
                servico.setPrice(preco);
                servico.setDuration(duracao);
                servico.setProId(proId);

                listaAtualizada.add(servico);
            }
        }

        return listaAtualizada;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemServicosBinding binding = ItemServicosBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);

        holder.binding.btnRemoveService.setOnClickListener(v -> exclude(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemServicosBinding binding;

        public ViewHolder(ItemServicosBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.txtServiceTitle.setText(String.format("Serviço %d", position + 1));
        }
    }
}
