package com.example.smartapp.fragments.registro;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.databinding.CriaServicoBinding;
import com.example.smartapp.viewmodels.CriarServicoViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CriarServicoFragment extends Fragment {

    private CriaServicoBinding binding;

    private CriarServicoViewModel viewModel;

    private NavController navController;
    private ServicoResponse serviceToAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CriaServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CriarServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
        setupObservers();
        setupView();
    }

    private void setupObservers() {
        viewModel.edited.observe(getViewLifecycleOwner(), wasEdited -> {
            if (wasEdited){
                Toast.makeText(getContext(), "Serviço editado com sucesso", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        });
        viewModel.added.observe(getViewLifecycleOwner(), wasEdited -> {
            if (wasEdited){
                Toast.makeText(getContext(), "Serviço adicionado com sucesso", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        });
    }

    private void setupView() {
        CriarServicoFragmentArgs args = CriarServicoFragmentArgs.fromBundle(getArguments());
        serviceToAdd = args.getServico();

        if (serviceToAdd != null){
            binding.edtNomeServico.setText(serviceToAdd.getName());
            binding.edtDescricaoSer.setText(serviceToAdd.getDescription());
            binding.edtSerpreco.setText(String.valueOf(serviceToAdd.getPrice()));
            binding.edtTempo.setText(String.valueOf(serviceToAdd.getDuration()));
            binding.txtTitle.setText("Editar serviço existente");

            binding.btnconfirmarServico.setText("Salvar serviço");
            binding.btnconfirmarServico.setBackgroundTintList(
                    ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.success_green))
            );
        }
    }

    private void setupListeners(){
        binding.btnVoltarServCad.setOnClickListener(v -> navController.popBackStack());

        binding.btnconfirmarServico.setOnClickListener(v -> {
            if(viewModel.validateField(
                    binding.edtNomeServico.getText().toString(),
                    binding.edtDescricaoSer.getText().toString(),
                    binding.edtSerpreco.getText().toString(),
                    binding.edtTempo.getText().toString()
            )){
                if (serviceToAdd != null){
                    viewModel.editService(
                            serviceToAdd,
                            binding.edtNomeServico.getText().toString(),
                            binding.edtDescricaoSer.getText().toString(),
                            binding.edtSerpreco.getText().toString(),
                            binding.edtTempo.getText().toString()
                            );
                } else {
                    viewModel.addService(
                            binding.edtNomeServico.getText().toString(),
                            binding.edtDescricaoSer.getText().toString(),
                            binding.edtSerpreco.getText().toString(),
                            binding.edtTempo.getText().toString()
                    );
                }
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
