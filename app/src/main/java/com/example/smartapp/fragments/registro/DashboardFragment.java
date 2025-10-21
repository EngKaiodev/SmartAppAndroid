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
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.smartapp.adapters.ProfissionalAdapter;
import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.databinding.DashboardBinding;
import com.example.smartapp.domain.models.ProfissionalModel;
import com.example.smartapp.viewmodels.DashboardViewModel;

import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends Fragment {
    private DashboardBinding binding;
    private DashboardViewModel viewModel;
    private NavController navController;
    private List<ProfissionalResponse> profissionalList;
    private ProfissionalAdapter profissionalAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DashboardBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(this);
        viewModel.loadProfissionais();

        setupObservers();
        setupListeners();
        setupView();

    }

    private void setupView() {
        binding.name.setText(viewModel.getUserName());
    }

    private void setupObservers() {
        viewModel.profissionais.observe(getViewLifecycleOwner(), lista -> {
            if (lista != null && !lista.isEmpty()) {
                profissionalList = lista;
                setupAdapters();
            }
        });
    }

    private void setupAdapters() {
        profissionalAdapter = new ProfissionalAdapter(
                profissionalList,
                profissionalModel -> {
                    navController.navigate(
                            DashboardFragmentDirections.
                                    actionAgendarServico(profissionalModel)
                    );
                },
                s -> {
                    navController.navigate(DashboardFragmentDirections.actionHomeToPrestadorPerfil(s.getId_profissional()));
                });
        binding.rvCatProfissional.setAdapter(profissionalAdapter);
        binding.rvCatProfissional.setLayoutManager(new GridLayoutManager(getContext(), 2));

    }


    private void setupListeners() {
        binding.btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(DashboardFragmentDirections.actionHomeToPerfilPrestador());
            }
        });

        binding.btnAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(DashboardFragmentDirections.actionHomeToAgendamentos());
            }
        });

        binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(DashboardFragmentDirections.actionHomeToCriarProduto());
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
