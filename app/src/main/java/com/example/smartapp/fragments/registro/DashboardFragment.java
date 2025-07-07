package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartapp.databinding.DashboardBinding;
import com.example.smartapp.viewmodels.DashboardViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint // 3
public class DashboardFragment extends Fragment {

    /**
     * 1 - CRIAR VIEWMODEL
     * 2 - ANOTAR VIEWMODEL
     * 3 - NO FRAGMENT ANOTAR COM ANDROIDENTRYPOINT
     * 4 - CRIAR VARIAVEL VIEWMODEL
     * 5 - INICIAR VARIAVEL VIEWMODEL
     */

    private DashboardBinding binding;
    private DashboardViewModel viewModel; // 4

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DashboardBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class); // 5
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
    }

    private void setupListeners() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
