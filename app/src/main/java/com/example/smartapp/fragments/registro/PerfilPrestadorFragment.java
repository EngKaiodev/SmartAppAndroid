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

import com.example.smartapp.adapters.AvalicaoAdapter;
import com.example.smartapp.adapters.ServicoAdapter;
import com.example.smartapp.core.domain.response.ProfissionalDetailResponse;
import com.example.smartapp.core.domain.response.ReviewSimpleResponse;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.databinding.PerfilPrestadorBinding;
import com.example.smartapp.viewmodels.PerfilPrestadorViewModel;


import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PerfilPrestadorFragment extends Fragment {

    private PerfilPrestadorBinding binding;
    private PerfilPrestadorViewModel viewModel;

    private NavController navController;

    private int id;
    private AvalicaoAdapter avalicaoAdapter;
    private List<ReviewSimpleResponse> reviews;

    private ServicoAdapter servicoAdapter;
    private List<ServicoResponse> servicos;

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
        PerfilPrestadorFragmentArgs args = PerfilPrestadorFragmentArgs.fromBundle(getArguments());

        id = args.getProId();
        viewModel.getDetails(id);


        setupListeners();
        setupObservers();
    }

    private void setupObservers(){
        viewModel.profissional.observe(getViewLifecycleOwner(), pro -> {
            if (pro != null){
                setupView(pro);
                reviews = pro.getReviews();
                servicos = pro.getServicos();
                setupAdapters();
            }
        });
    }

    private void setupAdapters() {
        avalicaoAdapter = new AvalicaoAdapter(reviews);

        binding.rvAvaliacao.setAdapter(avalicaoAdapter);
        binding.rvAvaliacao.setLayoutManager(new LinearLayoutManager(getContext()));

        servicoAdapter = new ServicoAdapter(servicos, false);

        binding.rvServico.setAdapter(servicoAdapter);
        binding.rvServico.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupView(ProfissionalDetailResponse pro) {
        binding.professionalName.setText(pro.getProfissional().getAltername());
        binding.professionalRating.setText(pro.getProfissional().getRate().toString());
        binding.professionalReviews.setText("(" + pro.getProfissional().getReviewsCount() + " avaliações)");
        binding.professionalLocation.setText(pro.getAddress().getAddress());
        binding.aboutName.setText("Sobre " + pro.getProfissional().getAltername());
        binding.professionalDescription.setText(pro.getProfissional().getDescription());
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
                navController.navigate(PerfilPrestadorFragmentDirections.agendarServicoFragment(id, null));
            }
        });

        binding.btnAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(PerfilPrestadorFragmentDirections.actionAvaliarTela());
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
