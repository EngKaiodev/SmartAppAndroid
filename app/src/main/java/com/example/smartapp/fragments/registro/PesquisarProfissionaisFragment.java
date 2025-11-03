package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.example.smartapp.adapters.ProfissionalListAdapter;
import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.databinding.PesquisarProfissionalBinding;
import com.example.smartapp.viewmodels.PesquisarProfissioanaisViewModel;

import java.util.List;
import java.util.function.Consumer;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PesquisarProfissionaisFragment extends Fragment {

    private PesquisarProfissionalBinding binding;
    private PesquisarProfissioanaisViewModel viewModel;
    private NavController navController;
    private ProfissionalListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PesquisarProfissionalBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PesquisarProfissioanaisViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);
        viewModel.loadPros();

        setupListeners();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.pros.observe(getViewLifecycleOwner(), profissionais -> {
            setupAdapter(profissionais);
        });
    }

    private void setupAdapter(List<ProfissionalResponse> profissionais) {
        adapter = new ProfissionalListAdapter(profissionais, profissionalResponse -> {
            navController.navigate(PesquisarProfissionaisFragmentDirections.perfilPrestadorFragment(profissionalResponse.getId_profissional()));
        });

        binding.rvPros.setAdapter(adapter);
        binding.rvPros.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupListeners() {
        binding.btnvoltarprest.setOnClickListener(v -> navController.popBackStack());

        binding.searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null){
                    adapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
