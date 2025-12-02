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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartapp.adapters.ServiceListAdapter;
import com.example.smartapp.core.domain.response.ServiceCompleteResponse;
import com.example.smartapp.core.watcher.SimpleTextWatcher;
import com.example.smartapp.databinding.BuscarServicoBinding;
import com.example.smartapp.viewmodels.BuscarServicoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BuscarServicoFragment extends Fragment {

    private BuscarServicoBinding binding;
    private BuscarServicoViewModel viewModel;
    private NavController navController;

    private ServiceListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BuscarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(BuscarServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);
        viewModel.loadServicos();

        setupListeners();
        setupObservers();
    }

    private void setupAdapters(List<ServiceCompleteResponse> servicos) {
        adapter = new ServiceListAdapter(servicos, selected -> {
            try {
                viewModel.loadPro(selected.getProfissionalId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        binding.rvtlistaservico.setAdapter(adapter);
        binding.rvtlistaservico.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvtlistaservico.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        );
    }

    private void setupListeners() {
        binding.btnvoltarbs.setOnClickListener(v -> navController.popBackStack());

        binding.searchQuery.addTextChangedListener(new SimpleTextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null){
                    adapter.filter(s.toString());
                }
            }
        });
    }

    private void setupObservers() {
        viewModel.servicos.observe(getViewLifecycleOwner(), this::setupAdapters);
        viewModel.pro.observe(getViewLifecycleOwner(), profissional -> {
            if (profissional == null) return;
            navController.navigate(BuscarServicoFragmentDirections.actionAgendar(profissional, null));
            viewModel.clearPro();
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}