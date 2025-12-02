package com.example.smartapp.fragments.registro;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.example.smartapp.activities.LoginActivity;
import com.example.smartapp.activities.MainActivity;
import com.example.smartapp.core.ErroHelper;
import com.example.smartapp.core.Mask;
import com.example.smartapp.core.watcher.SimpleTextWatcher;
import com.example.smartapp.databinding.CadastrarClienteBinding;
import com.example.smartapp.databinding.CadastrarServicoBinding;
import com.example.smartapp.domain.models.ErrorModel;
import com.example.smartapp.viewmodels.CadastrarClienteViewModel;
import com.google.firebase.messaging.FirebaseMessaging;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        binding.btnfinalizarCliente.setOnClickListener(v -> {
            if (fieldToValidated()) {
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                Log.w("FCM", "Falha ao pegar token", task.getException());
                                return;
                            }
                            String token = task.getResult();

                            Disposable disposable = viewModel.register(
                                    binding.etFullName.getText().toString(),
                                    binding.edtSenha.getText().toString(),
                                    binding.edttelefone.getText().toString(),
                                    binding.etEmailRegister.getText().toString(),
                                    binding.edtCpfCnpj.getText().toString(),
                                    2,
                                    binding.edtCep.getText().toString(),
                                    binding.edtEndenreco.getText().toString(),
                                    binding.edtNumero.getText().toString(),
                                    token
                            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    ErrorModel error = ErroHelper.getErrorModel(response);
                                    binding.tvError.setText(error.getMessage());
                                    binding.erroContainer.setVisibility(View.VISIBLE);
                                }
                            }, throwable -> {
                                // erro → exibir Toast, dialog, etc.
                                Toast.makeText(this.getContext(), "Erro ao registrar", Toast.LENGTH_SHORT).show();
                            });
                        });
            } else {
                binding.erroContainer.setVisibility(View.VISIBLE);
            }
        });
        binding.edtCep.addTextChangedListener(new SimpleTextWatcher() {

            boolean updating = false;

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String cep = Mask.unmask(charSequence.toString());

                if (cep.length() > 7) {
                    Disposable disposable = viewModel.buscarCep(cep).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(viaCepResponse -> {
                        String logradouro = viaCepResponse.getLogradouro();

                        if (logradouro == null){
                            Toast.makeText(getContext(), "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                            binding.edtEndenreco.requestFocus();
                        } else {
                            String endereco = viaCepResponse.toString();

                            binding.edtEndenreco.setText(endereco);
                            binding.edtNumero.requestFocus();
                        }

                    }, throwable -> {
                        Toast.makeText(getContext(), "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (updating) return;

                String original = s.toString();
                String masked = Mask.applyMaskCep(original);

                if (!original.equals(masked)) {
                    updating = true;
                    binding.edtCep.setText(masked);
                    binding.edtCep.setSelection(masked.length());
                    updating = false;
                }
            }
        });
        binding.edtCpfCnpj.addTextChangedListener(new SimpleTextWatcher() {
            boolean isUpdating = false;
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (isUpdating) return;

                String raw = s.toString();
                String formatted = Mask.maskDocument(raw);

                if (!raw.equals(formatted)) {
                    isUpdating = true;
                    binding.edtCpfCnpj.setText(formatted);
                    binding.edtCpfCnpj.setSelection(formatted.length());
                    isUpdating = false;
                }
            }
        });
        binding.edttelefone.addTextChangedListener(new SimpleTextWatcher() {
            boolean updating = false;
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (updating) return;

                String original = editable.toString();
                String masked = Mask.applyMaskTelefone(original);

                if (!original.equals(masked)) {
                    updating = true;
                    binding.edttelefone.setText(masked);
                    binding.edttelefone.setSelection(masked.length());
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

    public boolean fieldToValidated() {
        return viewModel.validatedField(binding.etFullName.getText().toString(), binding.etEmailRegister.getText().toString(), binding.edttelefone.getText().toString(), binding.edtCep.getText().toString(), binding.edtEndenreco.getText().toString(), binding.edtNumero.getText().toString(), binding.edtSenha.getText().toString(), binding.edtConfirmarSenha.getText().toString(), binding.edtCpfCnpj.getText().toString());
    }
}
