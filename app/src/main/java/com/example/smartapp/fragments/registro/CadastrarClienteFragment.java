package com.example.smartapp.fragments.registro;

import android.content.Intent;
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

import com.example.smartapp.activities.MainActivity;
import com.example.smartapp.databinding.CadastrarClienteBinding;
import com.example.smartapp.databinding.CadastrarServicoBinding;
import com.example.smartapp.viewmodels.CadastrarClienteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastrarClienteFragment extends Fragment {

    private CadastrarClienteBinding binding;
    private CadastrarClienteViewModel viewModel;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CadastrarClienteBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CadastrarClienteViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        setupListeners();
    }

    private void setupListeners() {
        binding.btnfinalizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
