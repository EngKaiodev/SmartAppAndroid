package com.example.smartapp.fragments.registro;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartapp.activities.MainActivity;
import com.example.smartapp.adapters.CreateServiceAdapter;
import com.example.smartapp.core.domain.request.ServicoRequest;
import com.example.smartapp.databinding.CadastrarServicoBinding;
import com.example.smartapp.viewmodels.CadastrarServicoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastrarServicoFragment extends Fragment {

    private CadastrarServicoBinding binding;
    private CadastrarServicoViewModel viewModel;
    private CreateServiceAdapter adapter;

    private int proId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CadastrarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CadastrarServicoViewModel.class);
        CadastrarServicoFragmentArgs args = CadastrarServicoFragmentArgs.fromBundle(getArguments());
        proId = args.getProId();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
        setupObservers();
        SetupView();
    }

    private void setupObservers() {
        viewModel.added.observe(getViewLifecycleOwner(), result -> {
            if (result){
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void SetupView() {
        adapter = new CreateServiceAdapter(1, proId);
        binding.rvServicos.setAdapter(adapter);
        binding.rvServicos.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupListeners() {
        binding.btnfinalizarPro.setOnClickListener(v -> {
            List<ServicoRequest> requests = adapter.getServicosAtualizados();
            if (requests.isEmpty()){
                new AlertDialog.Builder(getContext())
                        .setTitle("Atenção")
                        .setMessage("Deseja realmente continuar sem adicionar um serviço? \n Essa ação pode ser feita depois.")
                        .setPositiveButton("Sim", (dialog, which) -> {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        })
                        .setNegativeButton("Não", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setCancelable(false)
                        .show();

            } else {
                viewModel.addServices(requests);
            }
        });

        binding.btnAdcionarServico.setOnClickListener(view -> {
            adapter.addCount();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
