package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.core.Mask;
import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.core.domain.response.ViaCepResponse;
import com.example.smartapp.core.watcher.SimpleTextWatcher;
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

        viewModel.loadData();

        setupObservers();
        setupListeners();
    }

    private void setupObservers() {
        viewModel.myself.observe(getViewLifecycleOwner(), this::setupView);

        viewModel.edited.observe(getViewLifecycleOwner(), response -> {
            if (response){
                Toast.makeText(getContext(), "Informações salvar com sucesso", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        });

        viewModel.address.observe(getViewLifecycleOwner(), address -> {
            if (address != null){
                setupAddress(address);
            }
        });
    }

    private void setupAddress(ViaCepResponse address) {
        binding.edtEnderecocliente.setText(address.toString());
        binding.edtNumerocliente.requestFocus();
    }

    private void setupView(MyselfResponse response) {
        if (response != null){
            binding.edtNomecliente.setText(response.getCompleteName());
            binding.edtEmailcliente.setText(response.getEmail());
            binding.edtTelefonecliente.setText(response.getPhone());
            binding.edtCfpCnpjCliente.setText(response.getDocument());
            binding.edtCepcliente.setText(response.getAddress().getZipCode());
            binding.edtEnderecocliente.setText(response.getAddress().getAddress());
            binding.edtNumerocliente.setText(response.getAddress().getNumber());
        }
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
                viewModel.editData(
                        binding.edtNomecliente.getText().toString(),
                        binding.edtEmailcliente.getText().toString(),
                        binding.edtTelefonecliente.getText().toString(),
                        binding.edtCfpCnpjCliente.getText().toString(),
                        binding.edtCepcliente.getText().toString(),
                        binding.edtEnderecocliente.getText().toString(),
                        binding.edtNomecliente.getText().toString()
                );
            }else {
                binding.erroContainer.setVisibility(View.VISIBLE);
                binding.erroContainer.setVisibility(View.GONE);
            }
        });
        binding.edtCepcliente.addTextChangedListener(new SimpleTextWatcher() {
            boolean updating = false;
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String cep = Mask.unmask(charSequence.toString());
                if (cep.length() > 7){
                    viewModel.tryCep(cep);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (updating) return;

                String original = editable.toString();
                String masked = Mask.applyMaskCep(original);

                if (!original.equals(masked)) {
                    updating = true;
                    binding.edtCepcliente.setText(masked);
                    binding.edtCepcliente.setSelection(masked.length());
                    updating = false;
                }
            }
        });
        binding.edtCfpCnpjCliente.addTextChangedListener(new SimpleTextWatcher() {
            boolean isUpdating = false;

            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (isUpdating) return;

                String raw = editable.toString();
                String formatted = Mask.maskDocument(raw);

                if (!raw.equals(formatted)) {
                    isUpdating = true;
                    binding.edtCfpCnpjCliente.setText(formatted);
                    binding.edtCfpCnpjCliente.setSelection(formatted.length());
                    isUpdating = false;
                }
            }
        });
        binding.edtTelefonecliente.addTextChangedListener(new SimpleTextWatcher() {
            boolean updating = false;
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (updating) return;

                String original = editable.toString();
                String masked = Mask.applyMaskTelefone(original);

                if (!original.equals(masked)) {
                    updating = true;
                    binding.edtTelefonecliente.setText(masked);
                    binding.edtTelefonecliente.setSelection(masked.length());
                    updating = false;
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
