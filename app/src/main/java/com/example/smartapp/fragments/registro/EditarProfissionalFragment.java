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

import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.databinding.EditarProfissionalBinding;
import com.example.smartapp.viewmodels.EditarProfissionalViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditarProfissionalFragment extends Fragment {

    private EditarProfissionalBinding binding;
    private EditarProfissionalViewModel viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditarProfissionalBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(EditarProfissionalViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);
        viewModel.loadData();

        setupListeners();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.myself.observe(getViewLifecycleOwner(), response -> {
            if (response != null){
                setupData(response);
            }
        });
    }

    private void setupData(MyselfResponse response) {
        binding.edtNomePro.setText(response.getCompleteName());
        binding.edtEmailPro.setText(response.getEmail());
        binding.edtTelefonePro.setText(response.getPhone());
        binding.edtCpfCnpjPro.setText(response.getDocument());
        binding.edtCepPro.setText(response.getAddress().getZipCode());
        binding.edtEnderecoPro.setText(response.getAddress().getAddress());
        binding.edtNumeroPro.setText(response.getAddress().getNumber());
        binding.edtRazaoSocialPro.setText(response.getAlterName());
        binding.edtDescricaoPro.setText(response.getDescription());
    }

    private void setupListeners() {
        binding.btnvoltarProfisional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        binding.btnSalvarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.validateField(
                        binding.edtNomePro.getText().toString(),
                        binding.edtEmailPro.getText().toString(),
                        binding.edtTelefonePro.getText().toString(),
                        binding.edtCpfCnpjPro.getText().toString(),
                        binding.edtCepPro.getText().toString(),
                        binding.edtEnderecoPro.getText().toString(),
                        binding.edtNumeroPro.getText().toString(),
                        binding.edtRazaoSocialPro.getText().toString(),
                        binding.edtDescricaoPro.getText().toString(),
                        binding.edtNovasenhaPro.getText().toString(),
                        binding.edtConfimaNovaSenhaPro.getText().toString()


                )){

                }else {
                    binding.erroContainer.setVisibility(View.VISIBLE);
                    binding.erroContainer.setVisibility(View.GONE);
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
