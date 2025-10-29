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

import com.example.smartapp.databinding.EditarClienteBinding;
import com.example.smartapp.viewmodels.EditarClienteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditarClienteFragment extends Fragment {

    private EditarClienteBinding binding;
    private EditarClienteViewModel viewModel;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditarClienteBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(EditarClienteViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
    }

    private void setupListeners() {
        binding.btnvoltarcliente.setOnClickListener(v -> navController.popBackStack());

        binding.btnSalvar.setOnClickListener(v -> {
            if(viewModel.validateFragment(
                    binding.edtNomecliente.getText().toString(),
                    binding.edtEmailcliente.getText().toString(),
                    binding.edtTelefonecliente.getText().toString(),
                    binding.edtCfpCnpjCliente.getText().toString(),
                    binding.edtCepcliente.getText().toString(),
                    binding.edtEnderecocliente.getText().toString(),
                    binding.edtNomecliente.getText().toString(),
                    binding.edtnovasenha.getText().toString(),
                    binding.edtConfimarnovasenha.getText().toString()
            )){

            }else {
                binding.erroContainer.setVisibility(View.VISIBLE);
                binding.erroContainer.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
