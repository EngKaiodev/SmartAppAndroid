package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartapp.databinding.ConfiguracaoMensagemBinding;
import com.example.smartapp.viewmodels.ConfigurarMensagemViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ConfigurarMensagemFragment extends Fragment {

    private ConfiguracaoMensagemBinding binding;
    private ConfigurarMensagemViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ConfiguracaoMensagemBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ConfigurarMensagemViewModel.class);
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
