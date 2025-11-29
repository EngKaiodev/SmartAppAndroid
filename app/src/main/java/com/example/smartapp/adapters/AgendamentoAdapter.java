package com.example.smartapp.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.databinding.ItemAgendamentoBinding;

import java.util.List;

public class AgendamentoAdapter extends RecyclerView.Adapter<AgendamentoAdapter.ViewHolder> {

    private final List<ScheduleResponse> aList;
    private java.util.function.Consumer<ScheduleResponse> onAgendarClick;
    private int myID;
    private int type;

    public AgendamentoAdapter(int myID, int type, List<ScheduleResponse> aList, java.util.function.Consumer<ScheduleResponse> onAgendarClick) {
        this.aList = aList;
        this.onAgendarClick = onAgendarClick;
        this.myID = myID;
        this.type = type;
    }


    @NonNull
    @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAgendamentoBinding binding = ItemAgendamentoBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull AgendamentoAdapter.ViewHolder holder, int position) {
        ScheduleResponse aitem = aList.get(position);
        holder.binding(aitem, myID, type);

        //Colocar o click no botão aqui
        holder.binding.btnAction.setOnClickListener(v -> {
            if (onAgendarClick != null) {
                onAgendarClick.accept(aitem);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemAgendamentoBinding binding;

        public ViewHolder(ItemAgendamentoBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("ResourceAsColor")
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void binding(ScheduleResponse agendamento, int myID, int type) {
            binding.nome.setText(agendamento.getProfissional().getName());
            binding.servico.setText(agendamento.getServico().getName());
            binding.data.setText(agendamento.getHour().getString());
            binding.valor.setText(String.format("Valor: R$ %.2f", agendamento.getServico().getPrice()));
            binding.hora.setText(agendamento.getHour().toString());

            ScheduleResponse.StatusInfo info = agendamento.getStatusInfo();

            binding.status.setTextColor(info.getColor());
            binding.status.setText(info.getLabel());

            binding.statusIco.setImageResource(info.getIconRes());
            binding.statusIco.setColorFilter(info.getColor());

            switch (agendamento.getStatus()){
                case 0: {
                    binding.btnAction.setText("Confirmar");
                    binding.btnAction.setBackgroundColor(
                            ContextCompat.getColor(binding.getRoot().getContext(), R.color.success_green)
                    );
                    if (type == 2) {
                        binding.btnAction.setText("Reagendar");
                        binding.btnAction.setBackgroundColor(
                                ContextCompat.getColor(binding.getRoot().getContext(), R.color.indigo_600)
                        );
                    }
                    break;
                }
                case 1: {
                    binding.btnAction.setText("Reagendar");
                    binding.btnAction.setBackgroundColor(
                            ContextCompat.getColor(binding.getRoot().getContext(), R.color.indigo_600)
                    );
                    break;
                }
                case 3: {
                    binding.btnAction.setText("Avaliar");
                    binding.btnAction.setBackgroundColor(
                            ContextCompat.getColor(binding.getRoot().getContext(), R.color.notification_yellow)
                    );
                    if (agendamento.getProfissional().getId_profissional() == myID) binding.btnAction.setVisibility(ViewGroup.GONE);
                    break;
                }
                default: {
                    binding.btnAction.setVisibility(ViewGroup.GONE);
                    break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return aList != null ? aList.size() : 0;
    }
}
