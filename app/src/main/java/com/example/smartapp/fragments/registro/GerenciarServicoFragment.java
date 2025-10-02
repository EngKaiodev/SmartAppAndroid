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

import com.example.smartapp.adapters.AgendamentoAdapter;
import com.example.smartapp.adapters.ServicoAdapter;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.databinding.GerenciarServicoBinding;
import com.example.smartapp.domain.models.AgendamentoModel;
import com.example.smartapp.domain.models.CategoryModel;
import com.example.smartapp.domain.models.ServicoModel;
import com.example.smartapp.viewmodels.GerenciarServicoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarServicoFragment extends Fragment {

    private GerenciarServicoBinding binding;
    private GerenciarServicoViewModel viewModel;
    private NavController navController;

    private List<ServicoResponse> servicoList;
    private ServicoAdapter servicoAdapter;


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

        mockedList();
        setupListeners();
        setupAdapters();
    }

    private void mockedList(){
        servicoList = List.of(
                new ServicoResponse(1,1, "Paula","manicure", 30f, 30)
        );
    }

    private void setupAdapters(){
        servicoAdapter = new ServicoAdapter(servicoList, true);
        binding.rvServico.setAdapter(servicoAdapter);
        binding.rvServico.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }



    private void setupListeners() {
        binding.btnVoltarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        binding.btnAdcionarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(GerenciarServicoFragmentDirections.actionCriarServicoFragment());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
