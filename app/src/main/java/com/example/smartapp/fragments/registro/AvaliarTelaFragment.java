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


import com.example.smartapp.core.domain.response.ReviewSimpleResponse;
import com.example.smartapp.databinding.AvaliarTelaBinding;

import com.example.smartapp.domain.models.AvaliacaoModel;
import com.example.smartapp.viewmodels.AvaliarTelaViewModel;

import com.example.smartapp.adapters.AvalicaoAdapter;

import java.util.List;

public class AvaliarTelaFragment extends Fragment {

    private AvaliarTelaBinding binding;
    private AvaliarTelaViewModel viewModel;
    private NavController navController;
    private List<ReviewSimpleResponse> avaliacaoList;
    private AvalicaoAdapter avalicaoAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AvaliarTelaBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AvaliarTelaViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        mockedList();
        setupListeners();
        setupAdapters();
    }

    private void mockedList(){
    }

    private void setupAdapters(){
        avalicaoAdapter = new AvalicaoAdapter(avaliacaoList);
        binding.rvAvaliacliente.setAdapter(avalicaoAdapter);
        binding.rvAvaliacliente.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupListeners(){
        binding.btnVoltaraval.setOnClickListener(new View.OnClickListener() {
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
