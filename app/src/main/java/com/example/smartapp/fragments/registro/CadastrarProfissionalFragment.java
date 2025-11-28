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

import com.example.smartapp.core.ErroHelper;
import com.example.smartapp.core.Mask;
import com.example.smartapp.core.watcher.SimpleTextWatcher;
import com.example.smartapp.databinding.CadastrarProfissionalBinding;
import com.example.smartapp.domain.models.ErrorModel;
import com.example.smartapp.viewmodels.CadastrarProfissionalViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        binding.btnproximoPro.setOnClickListener(v -> {
            if (fieldsToValidade()) {
                Disposable disposable = viewModel.register(
                        binding.edtProfNome.getText().toString(),
                        binding.edtSenha.getText().toString(),
                        binding.edtProftelefone.getText().toString(),
                        binding.edtProfEmail.getText().toString(),
                        binding.edtProfCpfCnpj.getText().toString(),
                        1,
                        binding.edtProfRazaoSocial.getText().toString(),
                        binding.edtProfdescricao.getText().toString(),
                        binding.edtProfCep.getText().toString(),
                        binding.edtProfendereco.getText().toString(),
                        binding.edtProfnumero.getText().toString()
                                )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                    if (response.isSuccessful() && response.code() == 200) {
                        int proId = response.body().getId();
                        navController.navigate(CadastrarProfissionalFragmentDirections.cadastrarServicoFragment(proId));
                    } else {
                        ErrorModel error = ErroHelper.getErrorModel(response);
                        binding.tvError.setText(error.getMessage());
                        binding.erroContainer.setVisibility(View.VISIBLE);
                    }
                }, throwable -> {
                    Toast.makeText(getContext(), "Erro ao registrar", Toast.LENGTH_SHORT).show();
                });
            } else {
                binding.erroContainer.setVisibility(View.VISIBLE);
            }
        });

        binding.edtProfCep.addTextChangedListener(new SimpleTextWatcher() {
            boolean updating = false;

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String cep = Mask.unmask(charSequence.toString());

                if (cep.length() > 7) {
                    Disposable disposable = viewModel.buscarCep(cep)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(viaCepResponse -> {

                                String logradouro = viaCepResponse.getLogradouro();
                                String bairro = viaCepResponse.getBairro();
                                String cidade = viaCepResponse.getLocalidade();
                                String uf = viaCepResponse.getUf();

                                String endereco = logradouro + " - " + bairro + " - " + cidade + " / " + uf;

                                binding.edtProfendereco.setText(endereco);
                                binding.edtProfnumero.requestFocus();

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
                    binding.edtProfCep.setText(masked);
                    binding.edtProfCep.setSelection(masked.length());
                    updating = false;
                }
            }
        });

        binding.edtProfCpfCnpj.addTextChangedListener(new SimpleTextWatcher() {
            boolean isUpdating = false;

            @Override
            public void afterTextChanged(Editable s) {
                if (isUpdating) return;

                String raw = s.toString();
                String formatted = Mask.maskDocument(raw);

                if (!raw.equals(formatted)) {
                    isUpdating = true;
                    binding.edtProfCpfCnpj.setText(formatted);
                    binding.edtProfCpfCnpj.setSelection(formatted.length());
                    isUpdating = false;
                }
            }
        });

        binding.edtProftelefone.addTextChangedListener(new SimpleTextWatcher() {
            boolean updating = false;
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (updating) return;

                String original = s.toString();
                String masked = Mask.applyMaskTelefone(original);

                if (!original.equals(masked)) {
                    updating = true;
                    binding.edtProftelefone.setText(masked);
                    binding.edtProftelefone.setSelection(masked.length());
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

    public boolean fieldsToValidade() {
        return viewModel.validateField(
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
                binding.edtProfConfSenha.getText().toString());
    }
}
