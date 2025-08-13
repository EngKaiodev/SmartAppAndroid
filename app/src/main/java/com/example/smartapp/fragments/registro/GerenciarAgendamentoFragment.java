package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartapp.adapters.AgendamentoAdapter;
import com.example.smartapp.databinding.GerenciarAgendamentoBinding;
import com.example.smartapp.domain.models.AgendamentoModel;
import com.example.smartapp.viewmodels.GerenciarAgendamentoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarAgendamentoFragment extends Fragment {

    private GerenciarAgendamentoBinding binding;
    private GerenciarAgendamentoViewModel viewModel;
    private AgendamentoAdapter agendamentoAdapter;
    private List<AgendamentoModel> agendamentoList;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GerenciarAgendamentoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GerenciarAgendamentoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        mockedList();
        setupListeners();
        setupAdapters();
    }

    private void mockedList() {
        agendamentoList = List.of(
                new AgendamentoModel(1, "jose carlos", "Corte e Barba", "22/07/25", "14:35", "R$60,00")

        );
    }

    private void setupAdapters() {
        agendamentoAdapter = new AgendamentoAdapter(agendamentoList);

        binding.rvAgendamentosCategories.setAdapter(agendamentoAdapter);
        binding.rvAgendamentosCategories.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupListeners() {
        binding.btnvoltaragendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        binding.btnReagendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(GerenciarAgendamentoFragmentDirections.reagendarFragment());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
