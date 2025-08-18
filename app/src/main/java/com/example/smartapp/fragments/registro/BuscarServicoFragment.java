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
import com.example.smartapp.adapters.ServicoAdapter;
import com.example.smartapp.databinding.BuscarServicoBinding;
import com.example.smartapp.domain.models.HistoricoModel;
import com.example.smartapp.domain.models.ServicoModel;
import com.example.smartapp.viewmodels.BuscarServicoViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BuscarServicoFragment extends Fragment {

    private BuscarServicoBinding binding;
    private BuscarServicoViewModel viewModel;

    private NavController navController;

    private HistoricoAdapter historicoAdapter;
    private List<HistoricoModel> historicoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BuscarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(BuscarServicoViewModel.class);
        return  binding.getRoot();
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
       historicoList= List.of(
                new HistoricoModel(1,"Joao terceiro", "massagem", "11/05/2025", "R$ 130,00", "5" )
        );
    }

    private void setupAdapters(){
        historicoAdapter = new HistoricoAdapter(historicoList);

        historicoAdapter = new HistoricoAdapter(historicoList);
        binding.rvtlistaservico.setAdapter(historicoAdapter);
        binding.rvtlistaservico.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupListeners() {
        binding.btnvoltarbs.setOnClickListener(new View.OnClickListener() {
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
