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

import com.example.smartapp.adapters.AgendamentoAdapter;
import com.example.smartapp.databinding.GerenciarServicoBinding;
import com.example.smartapp.domain.models.AgendamentoModel;
import com.example.smartapp.domain.models.CategoryModel;
import com.example.smartapp.viewmodels.GerenciarServicoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarServicoFragment extends Fragment {

    private GerenciarServicoBinding binding;
    private GerenciarServicoViewModel viewModel;
    private NavController navController;
    private AgendamentoAdapter agendamentoAdapter;
    private List<AgendamentoModel> agendamentoList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GerenciarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GerenciarServicoViewModel.class);
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



    }

    private void setupListeners() {
        binding.btnVoltarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
