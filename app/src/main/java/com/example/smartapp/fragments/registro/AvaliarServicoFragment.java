package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.databinding.AvaliarServicoBinding;
import com.example.smartapp.viewmodels.AvaliarServicoViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AvaliarServicoFragment extends Fragment {

    private AvaliarServicoBinding binding;
    private AvaliarServicoViewModel viewModel;

    private NavController navController;

    private ImageView[] stars;
    private int rating;

    private ScheduleResponse schedule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AvaliarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AvaliarServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        AvaliarServicoFragmentArgs args = AvaliarServicoFragmentArgs.fromBundle(getArguments());
        schedule = args.getSchedule();

        stars = new ImageView[]{
                binding.star1,
                binding.star2,
                binding.star3,
                binding.star4,
                binding.star5,
        };

        setupListeners();
        setupView();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.sent.observe(getViewLifecycleOwner(), sent -> {
            if (sent){
                navController.popBackStack(navController.getGraph().getStartDestinationId(), false);
            }
        });
    }

    private void setupView() {
        binding.tvProfessionalName.setText(schedule.getProfissional().getName());
        binding.tvServiceName.setText(schedule.getServico().getName());
        binding.tvServiceDate.setText(schedule.getHour().getString());
    }

    private void setupListeners() {
        binding.btnvoltarsv.setOnClickListener(v -> navController.popBackStack());

        binding.btnSubmit.setOnClickListener(v -> {
            if (rating > 0) {
                String comment = binding.tvRatingText.getText() + ": " + binding.etComment.getText().toString();
                viewModel.sendReview(
                        schedule.getProfissional().getId_profissional(),
                        rating,
                        comment
                );
            }
        });

        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i].setOnClickListener(v -> setRating(index + 1));
        }

    }

    private void setRating(int selectedRating) {
        rating = selectedRating;

        updateStarsView();
        updateRatingText();
    }

    private void updateRatingText() {
        switch (rating) {
            case 1:
                binding.tvRatingText.setText("Péssimo");
                break;
            case 2:
                binding.tvRatingText.setText("Ruim");
                break;
            case 3:
                binding.tvRatingText.setText("Regular");
                break;
            case 4:
                binding.tvRatingText.setText("Bom");
                break;
            case 5:
                binding.tvRatingText.setText("Excelente");
                break;
            default:
                binding.tvRatingText.setText("Selecione uma avaliação");
        }
    }

    private void updateStarsView() {
        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].setImageResource(R.drawable.ic_star_filled);
            } else {
                stars[i].setImageResource(R.drawable.ic_star_empty);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
