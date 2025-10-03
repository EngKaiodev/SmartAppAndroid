package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartapp.core.domain.request.HoursRequest;
import com.example.smartapp.databinding.ConfiguracaoMensagemBinding;
import com.example.smartapp.databinding.GerenciarHorariosBinding;
import com.example.smartapp.viewmodels.ConfigurarMensagemViewModel;
import com.example.smartapp.viewmodels.GerenciarHorariosViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarHorariosFragment extends Fragment {

    private GerenciarHorariosBinding binding;
    private GerenciarHorariosViewModel viewModel;

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

        setupView();
        setupObservers();
        setupListeners();
    }

    private void setupObservers() {
        viewModel.add.observe(getViewLifecycleOwner(), response -> {
            if (response){
                Toast.makeText(getContext(), "Horários inseridos com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Algo deu errado, tente de novo mais tarde.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupListeners() {
        binding.btnSalvarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

    /*private List<HoursRequest> getHours(){
        List<Integer> selectedDays = new ArrayList<>();
        List<HoursRequest> hours = new ArrayList<>();

        List<CheckBox> checks = new ArrayList<>(Arrays.asList(
                binding.checkSegunda,
                binding.checkTerca,
                binding.checkQuarta,
                binding.checkQuinta,
                binding.checkSexta,
                binding.checkSabado,
                binding.checkDomingo
        ));
        int count = 1;
        for (CheckBox check: checks){
            if (check.isChecked()){
                selectedDays.add(count);
            }
            count++;
        }

        for(Integer day : selectedDays){
            hours.add(new HoursRequest(
                 day,
                 binding.timeInicioManha.getHour(),
                 binding.timeFimManha.getHour(),
                 binding.timeInicioTarde.getHour(),
                 binding.
            ))
        }
    }*/
}
