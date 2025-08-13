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

import com.example.smartapp.activities.LoginActivity;
import com.example.smartapp.databinding.EsqueceuSenhaCodigoBinding;
import com.example.smartapp.viewmodels.EsqueceuCodigoViewModel;

public class EsqueceuCodigoFragment extends Fragment {

    private EsqueceuSenhaCodigoBinding binding;

    private EsqueceuCodigoViewModel viewModel;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EsqueceuSenhaCodigoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(EsqueceuCodigoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
    }

    private void setupListeners(){
        binding.btnVoltarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        binding.btnVerificarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        }) ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
