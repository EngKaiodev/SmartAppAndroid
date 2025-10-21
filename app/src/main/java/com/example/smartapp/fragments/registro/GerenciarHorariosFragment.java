package com.example.smartapp.fragments.registro;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.core.domain.request.HoursRequest;
import com.example.smartapp.databinding.ConfiguracaoMensagemBinding;
import com.example.smartapp.databinding.GerenciarHorariosBinding;
import com.example.smartapp.viewmodels.ConfigurarMensagemViewModel;
import com.example.smartapp.viewmodels.GerenciarHorariosViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarHorariosFragment extends Fragment {

    private GerenciarHorariosBinding binding;
    private GerenciarHorariosViewModel viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GerenciarHorariosBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GerenciarHorariosViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        setupView();
        setupObservers();
        setupListeners();
    }

    private void setupObservers() {
        viewModel.add.observe(getViewLifecycleOwner(), response -> {
            if (response) {
                Toast.makeText(getContext(), "Horários inseridos com sucesso!", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            } else {
                Toast.makeText(getContext(), "Algo deu errado, tente de novo mais tarde.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupListeners() {
        binding.btnSalvarHorario.setOnClickListener(view -> {
            viewModel.insertHourList(getHours());
        });
        binding.btnVoltarHorarios.setOnClickListener(v -> navController.popBackStack());
    }

    private void setupView() {
        binding.timeInicioManha.setIs24HourView(true);
        binding.timeInicioTarde.setIs24HourView(true);
        binding.timeFimManha.setIs24HourView(true);
        binding.timeFimTarde.setIs24HourView(true);

        binding.timeInicioManha.setHour(8);
        binding.timeInicioManha.setMinute(0);
        binding.timeFimManha.setHour(12);
        binding.timeFimManha.setMinute(0);

        binding.timeInicioTarde.setHour(14);
        binding.timeInicioTarde.setMinute(0);
        binding.timeFimTarde.setHour(18);
        binding.timeFimTarde.setMinute(0);
    }

    private List<HoursRequest> getHours() {
        List<Integer> selectedDays = new ArrayList<>();
        List<HoursRequest> hours = new ArrayList<>();

        List<CheckBox> checks = Arrays.asList(
                binding.checkSegunda,
                binding.checkTerca,
                binding.checkQuarta,
                binding.checkQuinta,
                binding.checkSexta,
                binding.checkSabado,
                binding.checkDomingo
        );

        int count = 1;
        for (CheckBox check : checks) {
            if (check.isChecked()) {
                selectedDays.add(count);
            }
            count++;
        }

        if (selectedDays.isEmpty()) {
            return hours;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return hours;
        }

        LocalDate today = LocalDate.now();
        int todayWeekday = today.getDayOfWeek().getValue();

        int initHourMorning = binding.timeInicioManha.getHour();
        int initMinuteMorning = binding.timeInicioManha.getMinute();

        int endHourMorning = binding.timeFimManha.getHour();
        int endMinuteMorning = binding.timeFimManha.getMinute();

        int initHourAfternoon = binding.timeInicioTarde.getHour();
        int initMinuteAfternoon = binding.timeInicioTarde.getMinute();

        int endHourAfternoon = binding.timeFimTarde.getHour();
        int endMinuteAfternoon = binding.timeFimTarde.getMinute();

        for (Integer day : selectedDays) {
            int diff = day - todayWeekday;
            if (diff <= 0) diff = diff + 7;
            LocalDate date = today.plusDays(diff);

            // Manhã
            LocalDateTime initMorning = LocalDateTime.of(date, LocalTime.of(initHourMorning, initMinuteMorning));
            LocalDateTime endMorning = LocalDateTime.of(date, LocalTime.of(endHourMorning, endMinuteMorning));

            while (!initMorning.isAfter(endMorning.minusMinutes(30))) {
                LocalDateTime slotEnd = initMorning.plusMinutes(30);
                hours.add(new HoursRequest(
                        viewModel.getId(),
                        initMorning,
                        slotEnd,
                        "opened"
                ));
                initMorning = slotEnd;
            }

            // Tarde
            LocalDateTime initAfternoon = LocalDateTime.of(date, LocalTime.of(initHourAfternoon, initMinuteAfternoon));
            LocalDateTime endAfternoon = LocalDateTime.of(date, LocalTime.of(endHourAfternoon, endMinuteAfternoon));

            while (!initAfternoon.isAfter(endAfternoon.minusMinutes(30))) {
                LocalDateTime slotEnd = initAfternoon.plusMinutes(30);
                hours.add(new HoursRequest(
                        viewModel.getId(),
                        initAfternoon,
                        slotEnd,
                        "opened"
                ));
                initAfternoon = slotEnd;
            }
        }
        for (HoursRequest h : hours) {
            Log.i("Vendo lista", h.toString());
        }
        return hours;
    }

}
