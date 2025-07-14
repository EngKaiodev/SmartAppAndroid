package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartapp.databinding.AgendarServicoBinding;
import com.example.smartapp.viewmodels.AgendarServicoViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AgendarServicoFragment extends Fragment {

    private AgendarServicoBinding binding;
    private AgendarServicoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AgendarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AgendarServicoViewModel.class);
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
