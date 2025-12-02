package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartapp.adapters.AgendamentoAdapter;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.databinding.GerenciarAgendamentoBinding;
import com.example.smartapp.viewmodels.GerenciarAgendamentoViewModel;

import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarAgendamentoFragment extends Fragment {

    private GerenciarAgendamentoBinding binding;
    private GerenciarAgendamentoViewModel viewModel;
    private AgendamentoAdapter agendamentoAdapter;
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
        viewModel.loadSchedulers();

        setupObservers();

        setupSpinners();
        setupListeners();
    }

    private void setupObservers() {
        viewModel.schedules.observe(getViewLifecycleOwner(), this::setupAdapters);
    }

    private void setupSpinners() {
        List<String> statusOptions = Arrays.asList(
                "TODOS",
                "A CONFIRMAR",
                "CONCLUIDOS",
                "CANCELADOS"
        );

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                statusOptions
        );
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.statusFilter.setAdapter(statusAdapter);

        // Configurar spinner de período
        List<String> periodOptions = Arrays.asList(
                "Todos os períodos",
                "Próximos 7 dias",
                "Próximos 30 dias",
                "Agendamentos passados"
        );

        ArrayAdapter<String> periodAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                periodOptions
        );
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.periodFilter.setAdapter(periodAdapter);

        binding.statusFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.periodFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.clearFilterButton.setOnClickListener(v -> {
            binding.statusFilter.setSelection(0);
            binding.periodFilter.setSelection(0);
            applyFilters();
        });
    }

    private void applyFilters() {
        if (agendamentoAdapter != null){
            String selectedStatus = binding.statusFilter.getSelectedItem().toString();
            String selectedPeriod = binding.periodFilter.getSelectedItem().toString();

            agendamentoAdapter.filter(selectedStatus, selectedPeriod);
        }
    }

    private void cleanFilter(){
        if (agendamentoAdapter != null){
            binding.statusFilter.setSelection(0);
            binding.periodFilter.setSelection(0);
            applyFilters();
        }
    }


    private void setupAdapters(List<ScheduleResponse> schedules) {
        agendamentoAdapter = new AgendamentoAdapter(
                schedules,
                viewModel.getId(),
                viewModel.getType(),
                selectedSchedule -> {
            navController.navigate(GerenciarAgendamentoFragmentDirections.reagendarFragment(selectedSchedule));
        });

        binding.rvAgendamentosCategories.setAdapter(agendamentoAdapter);
        binding.rvAgendamentosCategories.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupListeners() {
        binding.btnvoltaragendamento.setOnClickListener(v -> navController.popBackStack());

        binding.clearFilterButton.setOnClickListener(v -> cleanFilter());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}