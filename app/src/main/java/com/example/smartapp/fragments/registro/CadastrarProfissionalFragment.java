package com.example.smartapp.fragments.registro;

import android.content.Intent;
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

import com.example.smartapp.activities.MainActivity;
import com.example.smartapp.databinding.CadastrarProfissionalBinding;
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

        binding.edtProfCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 7){
                    Disposable disposable = viewModel.buscarCep(charSequence.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(viaCepResponse -> {

                                String logradouro = viaCepResponse.getLogradouro();
                                String bairro = viaCepResponse.getBairro();
                                String cidade = viaCepResponse.getLocalidade();
                                String uf = viaCepResponse.getUf();

                                String endereco = logradouro + " - " + bairro + " - " + cidade + " / " + uf;

                                binding.edtProfendereco.setText(endereco);

                            }, throwable -> {
                                Toast.makeText(getContext(), "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                                throwable.printStackTrace();
                            });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
