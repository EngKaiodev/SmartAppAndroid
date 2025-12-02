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


import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.ReviewData;
import com.example.smartapp.core.domain.response.ReviewSimpleResponse;
import com.example.smartapp.core.domain.response.ReviewToList;
import com.example.smartapp.databinding.AvaliarTelaBinding;

import com.example.smartapp.domain.models.AvaliacaoModel;
import com.example.smartapp.viewmodels.AvaliarTelaViewModel;

import com.example.smartapp.adapters.AvalicaoAdapter;

import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AvaliarTelaFragment extends Fragment {

    private AvaliarTelaBinding binding;
    private AvaliarTelaViewModel viewModel;
    private NavController navController;
    private List<ReviewSimpleResponse> avaliacaoList;


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
        AvaliarTelaFragmentArgs args = AvaliarTelaFragmentArgs.fromBundle(getArguments());

        viewModel.loadReviews(args.getProId());

        setupListeners();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.reviews.observe(getViewLifecycleOwner(), data -> {
            List<ReviewSimpleResponse> responses = data.getReviewsList()
                    .stream()
                    .map(ReviewToList::toResponse)
                    .toList();

            avaliacaoList = responses;

            setupAdapters();
            setupView(data.getData());

        });
    }

    private void setupView(ReviewData data) {
        if (data == null) return;
        // Média das avaliações
        binding.totalRating.setText(String.format("%.2f", data.getMedia_avaliacoes()));

        // Definir estrelas cheias de acordo com a média
        int mediaInt = (int) Math.round(data.getMedia_avaliacoes());
        binding.star1.setImageResource(mediaInt >= 1 ? R.drawable.ic_star : R.drawable.ic_star_empty);
        binding.star2.setImageResource(mediaInt >= 2 ? R.drawable.ic_star : R.drawable.ic_star_empty);
        binding.star3.setImageResource(mediaInt >= 3 ? R.drawable.ic_star : R.drawable.ic_star_empty);
        binding.star4.setImageResource(mediaInt >= 4 ? R.drawable.ic_star : R.drawable.ic_star_empty);
        binding.star5.setImageResource(mediaInt >= 5 ? R.drawable.ic_star : R.drawable.ic_star_empty);

        // Contagens de cada nota
        binding.count5.setText(String.valueOf(data.getNota_5()));
        binding.count4.setText(String.valueOf(data.getNota_4()));
        binding.count3.setText(String.valueOf(data.getNota_3()));
        binding.count2.setText(String.valueOf(data.getNota_2()));
        binding.count1.setText(String.valueOf(data.getNota_1()));

        // Total de avaliações
        binding.totalReviews.setText("Baseado em " + data.getTotal_avaliacoes() + " avaliações");

        // Atualizar progress bars
        long total = data.getTotal_avaliacoes() > 0 ? data.getTotal_avaliacoes() : 1; // evitar divisão por zero
        binding.progress5.setProgress((int) (data.getNota_5() * 100 / total));
        binding.progress4.setProgress((int) (data.getNota_4() * 100 / total));
        binding.progress3.setProgress((int) (data.getNota_3() * 100 / total));
        binding.progress2.setProgress((int) (data.getNota_2() * 100 / total));
        binding.progress1.setProgress((int) (data.getNota_1() * 100 / total));
    }



    private void setupAdapters(){
        AvalicaoAdapter avalicaoAdapter = new AvalicaoAdapter(avaliacaoList);
        binding.rvAvaliacliente.setAdapter(avalicaoAdapter);
        binding.rvAvaliacliente.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupListeners(){
        binding.btnVoltaraval.setOnClickListener(v -> navController.popBackStack());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
