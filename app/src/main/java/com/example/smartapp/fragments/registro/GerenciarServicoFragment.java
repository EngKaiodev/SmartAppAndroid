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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartapp.activities.MainActivity;
import com.example.smartapp.adapters.ServicoAdapter;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.databinding.GerenciarServicoBinding;
import com.example.smartapp.viewmodels.GerenciarServicoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarServicoFragment extends Fragment {

    private GerenciarServicoBinding binding;
    private GerenciarServicoViewModel viewModel;
    private NavController navController;

    private List<ServicoResponse> servicoList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GerenciarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GerenciarServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModel.loadServices();

        setupListeners();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.services.observe(getViewLifecycleOwner(), list -> {
            servicoList = list;
            setupAdapters();
        });
    }
    private void setupAdapters(){
        ServicoAdapter servicoAdapter = new ServicoAdapter(servicoList,
                clicked -> navController.navigate(GerenciarServicoFragmentDirections.actionCriarServicoFragment(clicked)),
                toDelete -> tryDelete(toDelete),
                true
                );
        binding.rvServico.setAdapter(servicoAdapter);
        binding.rvServico.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void tryDelete(ServicoResponse toDelete) {
        new AlertDialog.Builder(getContext())
                .setTitle("Atenção")
                .setMessage("Deseja realmente deletar este serviço? \n Essa ação não poderá ser desfeita.")
                .setPositiveButton("Sim", (dialog, which) -> {
                    viewModel.deleteService(toDelete.getId());
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setCancelable(false)
                .show();
    }

    private void setupListeners() {
        binding.btnVoltarServico.setOnClickListener(v -> navController.popBackStack());

        binding.btnAdcionarServico.setOnClickListener(v -> navController.navigate(GerenciarServicoFragmentDirections.actionCriarServicoFragment(null)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
