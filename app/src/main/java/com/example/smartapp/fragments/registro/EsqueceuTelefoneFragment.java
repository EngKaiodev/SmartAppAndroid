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

import com.example.smartapp.databinding.EsqueceuSenhaTelefoneBinding;
import com.example.smartapp.viewmodels.EsqueceuTelefoneViewModel;

public class EsqueceuTelefoneFragment extends Fragment {

    private EsqueceuSenhaTelefoneBinding binding;

    private EsqueceuTelefoneViewModel viewModel;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EsqueceuSenhaTelefoneBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(EsqueceuTelefoneViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
    }

    private void setupListeners(){
        binding.btnVoltarsenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });

        binding.btnEnviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(EsqueceuTelefoneFragmentDirections.actionRecuperarCodigo());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
