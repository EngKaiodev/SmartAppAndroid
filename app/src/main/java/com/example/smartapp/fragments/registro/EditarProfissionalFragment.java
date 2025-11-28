package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.text.Editable;
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
import com.example.smartapp.core.watcher.SimpleTextWatcher;
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
        viewModel.edited.observe(getViewLifecycleOwner(), response -> {
            if (response){
                Toast.makeText(getContext(), "Informações salvar com sucesso", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        });
        viewModel.address.observe(getViewLifecycleOwner(), address -> {
            if (address != null){
                binding.edtEnderecoPro.setText(address.toString());
                binding.edtNumeroPro.requestFocus();
            } else {
                Toast.makeText(getContext(), "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
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
        binding.btnvoltarProfisional.setOnClickListener(v -> navController.popBackStack());
        binding.btnSalvarPro.setOnClickListener(v -> {
            if(viewModel.validateField(
                    binding.edtNomePro.getText().toString(),
                    binding.edtEmailPro.getText().toString(),
                    binding.edtTelefonePro.getText().toString(),
                    binding.edtCpfCnpjPro.getText().toString(),
                    binding.edtCepPro.getText().toString(),
                    binding.edtEnderecoPro.getText().toString(),
                    binding.edtNumeroPro.getText().toString()
            )){
                viewModel.editData(
                        binding.edtNomePro.getText().toString(),
                        binding.edtEmailPro.getText().toString(),
                        binding.edtTelefonePro.getText().toString(),
                        binding.edtCpfCnpjPro.getText().toString(),
                        binding.edtCepPro.getText().toString(),
                        binding.edtEnderecoPro.getText().toString(),
                        binding.edtNumeroPro.getText().toString(),
                        binding.edtRazaoSocialPro.getText().toString(),
                        binding.edtDescricaoPro.getText().toString()
                );
            }else {
                binding.erroContainer.setVisibility(View.VISIBLE);
                binding.erroContainer.setVisibility(View.GONE);
            }
        });
        binding.edtCepPro.addTextChangedListener(new SimpleTextWatcher() {
            boolean updating = false;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                String cep = Mask.unmask(s.toString());

                if (cep.length() > 7){
                    viewModel.loadAddress(cep);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (updating) return;

                String original = editable.toString();
                String masked = Mask.applyMaskCep(original);

                if (!original.equals(masked)) {
                    updating = true;
                    binding.edtCepPro.setText(masked);
                    binding.edtCepPro.setSelection(masked.length());
                    updating = false;
                }
            }
        });
        binding.edtTelefonePro.addTextChangedListener(new SimpleTextWatcher() {
            boolean updating = false;
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (updating) return;

                String original = editable.toString();
                String masked = Mask.applyMaskTelefone(original);

                if (!original.equals(masked)) {
                    updating = true;
                    binding.edtTelefonePro.setText(masked);
                    binding.edtTelefonePro.setSelection(masked.length());
                    updating = false;
                }
            }
        });
        binding.edtCpfCnpjPro.addTextChangedListener(new SimpleTextWatcher() {
            boolean isUpdating = false;
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (isUpdating) return;

                String raw = editable.toString();
                String formatted = Mask.maskDocument(raw);

                if (!raw.equals(formatted)) {
                    isUpdating = true;
                    binding.edtCpfCnpjPro.setText(formatted);
                    binding.edtCpfCnpjPro.setSelection(formatted.length());
                    isUpdating = false;
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
