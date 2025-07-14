package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartapp.databinding.GerenciarNotificacoesBinding;
import com.example.smartapp.viewmodels.GerenciarNotificacoesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarNotificacoesFragment extends Fragment {

    private GerenciarNotificacoesBinding binding;
    private GerenciarNotificacoesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GerenciarNotificacoesBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GerenciarNotificacoesViewModel.class);
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
