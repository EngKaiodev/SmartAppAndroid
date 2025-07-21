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

import com.example.smartapp.databinding.DashboardBinding;
import com.example.smartapp.viewmodels.DashboardViewModel;

import com.example.smartapp.fragments.registro.DashboardFragmentDirections;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends Fragment {
    private DashboardBinding binding;
    private DashboardViewModel viewModel;
    private NavController navController; // 1 CRIA NAV CONTROLLER

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DashboardBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(this); // 2 INSTACIA NAV CONTROLER

        setupListeners();
    }

    /**
     * COLOCA TODAS AS NAVEGAÇÕES PELO NOME DA ACTION.
     * SE NÃO APARECER A ACTION, PROCURE PARA VER SE NÃO ESTÁ DUPLICADO ENTRE OS NAVS
     */
    private void setupListeners() {
        binding.btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(DashboardFragmentDirections.actionHomeToPerfilPrestador());
            }
        });

        binding.btnAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(DashboardFragmentDirections.actionHomeToAgendamentos());
            }
        });

       binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               navController.navigate(DashboardFragmentDirections.actionHomeToCriarProduto());
           }
       });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
