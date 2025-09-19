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
import com.example.smartapp.databinding.CadastrarServicoBinding;
import com.example.smartapp.viewmodels.CadastrarServicoViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastrarServicoFragment extends Fragment {

    private CadastrarServicoBinding binding;
    private CadastrarServicoViewModel viewModel;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CadastrarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CadastrarServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        setupListeners();
    }

    private void setupListeners() {
        binding.btnfinalizarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                        /*viewModel.validateField(
                        binding.edtNomeservico.getText().toString(),
                        binding.edtDescricaoServico.getText().toString(),
                        binding.edtPrecoServico.getText().toString())*/
                        true
                ){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
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
