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
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.databinding.BuscarProServBinding;
import com.example.smartapp.viewmodels.BuscarProServViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BuscarProServFragment extends Fragment {

    private BuscarProServBinding binding;
   private BuscarProServViewModel viewModel;

   private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BuscarProServBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(BuscarProServViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);
        
        setupListeners();
    }

    private void setupListeners() {
        binding.btnbuscapro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(BuscarProServFragmentDirections.actionBuscarProdutoToBuscarProfissional());
            }
        });

        binding.btnbuscaserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(BuscarProServFragmentDirections.actionBuscarProdutoToBuscarServico());
            }
        });

        binding.btnvoltarpesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
