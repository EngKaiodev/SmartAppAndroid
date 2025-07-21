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

import com.example.smartapp.databinding.GerenciarAgendamentoBinding;
import com.example.smartapp.viewmodels.GerenciarAgendamentoViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarAgendamentoFragment extends Fragment {

    private GerenciarAgendamentoBinding binding;
    private GerenciarAgendamentoViewModel viewModel;

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

        setupListeners();
    }

    private void setupListeners() {
        binding.btnvoltaragendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        //falta colocar uma lista para continuar para as proximas telas
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
