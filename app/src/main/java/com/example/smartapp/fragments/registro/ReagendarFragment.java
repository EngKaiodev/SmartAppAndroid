package com.example.smartapp.fragments.registro;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.R;
import com.example.smartapp.activities.MainActivity;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.databinding.ReagendarBinding;
import com.example.smartapp.viewmodels.ReagendarViewModel;

import java.time.LocalDateTime;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReagendarFragment extends Fragment {

    private ReagendarBinding binding;
    private ReagendarViewModel viewModel;

    private NavController navController;

    private ScheduleResponse selectedSchedule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ReagendarBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ReagendarViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        ReagendarFragmentArgs args = ReagendarFragmentArgs.fromBundle(getArguments());
        selectedSchedule = args.getSchedule();

        setupListeners();
        setupView();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.changed.observe(getViewLifecycleOwner(), changed -> {
            if (changed) {
                navController.popBackStack();
            }
        });
    }

    private void setupView() {
        if (selectedSchedule != null) {
            binding.name.setText(selectedSchedule.getProfissional().getName());
            binding.service.setText(selectedSchedule.getServico().getName());
            binding.date.setText(selectedSchedule.getHour().getString());
            binding.hour.setText(selectedSchedule.getHour().toString());
            binding.price.setText(selectedSchedule.getServico().returnPrice());

            ScheduleResponse.StatusInfo status = selectedSchedule.getStatusInfo();

            binding.statusLabel.setTextColor(status.getColor());
            binding.status.setTextColor(status.getColor());
            binding.status.setText(status.getLabel());

            switch (selectedSchedule.getStatus()) {
                case 0: {
                    binding.btnReview.setVisibility(View.GONE);
                    binding.btnDone.setVisibility(View.GONE);
                    break;
                }
                case 1: {
                    binding.btnConfirm.setVisibility(View.GONE);
                    binding.btnReview.setVisibility(View.GONE);
                    break;
                }
                case 3: {
                    binding.btncancelar.setVisibility(View.GONE);
                    binding.btnReagendar.setVisibility(View.GONE);
                    binding.btnConfirm.setVisibility(View.GONE);
                    binding.btnDone.setVisibility(View.GONE);
                    break;
                }
            }

            if (selectedSchedule.getHour().getInitHour().isBefore(LocalDateTime.now())){
                binding.btncancelar.setVisibility(View.GONE);
                binding.btnReagendar.setVisibility(View.GONE);
            }
        }
    }

    private void setupListeners() {
        binding.btnvoltardetalheag.setOnClickListener(v -> navController.popBackStack());

        binding.btnReagendar.setOnClickListener(v -> navController
                .navigate(
                        ReagendarFragmentDirections
                                .agendarServicoFragment(
                                        selectedSchedule.getProfissional(),
                                        selectedSchedule
                                )
                )
        );

        binding.btncancelar.setOnClickListener(v -> new AlertDialog.Builder(getContext())
                .setTitle("Atenção")
                .setMessage("Deseja realmente cancelar seu agendamento? \n Essa ação NÃO pode ser desfeita.")
                .setPositiveButton("Sim", (dialog, which) -> {
                    viewModel.cancelSchedule(selectedSchedule.getId());
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setCancelable(false)
                .show());

        binding.btnConfirm.setOnClickListener(v -> viewModel.confirmSchedule(selectedSchedule.getId()));

        binding.btnReview.setOnClickListener(v -> navController.navigate(ReagendarFragmentDirections.actionToReview(selectedSchedule)));

        binding.btnDone.setOnClickListener(v -> new AlertDialog.Builder(getContext())
                .setTitle("Atenção")
                .setMessage("Deseja realmente finalizar seu serviço? \n Essa ação NÃO pode ser desfeita.")
                .setPositiveButton("Sim", (dialog, which) -> {
                    viewModel.finishSchedule(selectedSchedule.getId());
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setCancelable(false)
                .show());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
