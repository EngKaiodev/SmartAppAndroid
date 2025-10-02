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

import com.example.smartapp.databinding.PesquisarProfissionalBinding;
import com.example.smartapp.viewmodels.PesquisarProfissioanaisViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PesquisarProfissionaisFragment extends Fragment {

    private PesquisarProfissionalBinding binding;
    private PesquisarProfissioanaisViewModel viewModel;

    private NavController navController;

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
        setupListeners();
    }

    private void setupListeners() {
        binding.btnvoltarprest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        binding.btnprestardorEncontrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(PesquisarProfissionaisFragmentDirections.perfilPrestadorFragment(0));
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
