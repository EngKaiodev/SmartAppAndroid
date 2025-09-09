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
import com.example.smartapp.databinding.CadastrarProfissionalBinding;
import com.example.smartapp.viewmodels.CadastrarProfissionalViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastrarProfissionalFragment extends Fragment {

    private CadastrarProfissionalBinding binding;
    private CadastrarProfissionalViewModel viewModel;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CadastrarProfissionalBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CadastrarProfissionalViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        setupListeners();
    }

    private void setupListeners() {
        binding.btnproximoPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.validateField(
                        binding.edtProfNome.getText().toString(),
                        binding.edtProfEmail.getText().toString(),
                        binding.edtProftelefone.getText().toString(),
                        binding.edtProfCpfCnpj.getText().toString(),
                        binding.edtProfCep.getText().toString(),
                        binding.edtProfendereco.getText().toString(),
                        binding.edtProfnumero.getText().toString(),
                        binding.edtProfRazaoSocial.getText().toString(),
                        binding.edtProfdescricao.getText().toString(),
                        binding.edtSenha.getText().toString(),
                        binding.edtProfConfSenha.getText().toString()

                )){
                    navController.navigate(CadastrarProfissionalFragmentDirections.cadastrarServicoFragment());
                }else {
                    binding.erroContainer.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
