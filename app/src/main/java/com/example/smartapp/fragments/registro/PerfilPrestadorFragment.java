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

import com.example.smartapp.databinding.PerfilPrestadorBinding;
import com.example.smartapp.viewmodels.PerfilPrestadorViewModel;



import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PerfilPrestadorFragment extends Fragment {

    private PerfilPrestadorBinding binding;
    private PerfilPrestadorViewModel viewModel;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PerfilPrestadorBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PerfilPrestadorViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(this);
        setupListeners();
    }

    private void setupListeners() {
        binding.btnvoltarpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        binding.btnAgendarServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(AgendarServicoFragmentDirections.agendarServicoFragment());
            }
        });

        binding.btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(AgendarServicoFragmentDirections.avaliarPrestardorFragment());
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
