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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartapp.adapters.HistoricoAdapter;
import com.example.smartapp.core.domain.response.HistoricResponse;
import com.example.smartapp.databinding.HistoricoServicoBinding;
import com.example.smartapp.domain.models.HistoricoModel;
import com.example.smartapp.viewmodels.HistoricoServicoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoricoServicoFragment extends Fragment {

    private HistoricoServicoBinding binding;
    private HistoricoServicoViewModel viewModel;

    private NavController navController;

    private List<HistoricResponse> historicoList;
    private HistoricoAdapter historicoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HistoricoServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(HistoricoServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);
        viewModel.loadHistoricos();

        setupListeners();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.historicos.observe(getViewLifecycleOwner(), historicos -> {
            historicoList = historicos;
            setupAdapters();
        });
    }

    private void setupAdapters(){
        historicoAdapter = new HistoricoAdapter(historicoList, historicoModel -> {
            //todo inserir aqui onde vai
        });
        binding.rvhistoricoServico.setAdapter(historicoAdapter);
        binding.rvhistoricoServico.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupListeners() {
        binding.btnvoltarhist.setOnClickListener(v -> navController.popBackStack());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
