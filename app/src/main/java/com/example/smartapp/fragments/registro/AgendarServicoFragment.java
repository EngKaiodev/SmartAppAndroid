package com.example.smartapp.fragments.registro;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.R;
import com.example.smartapp.core.domain.response.HourResponse;
import com.example.smartapp.core.domain.response.ProfissionalResponse;
import com.example.smartapp.core.domain.response.ScheduleResponse;
import com.example.smartapp.core.domain.response.ServicoResponse;
import com.example.smartapp.databinding.AgendarServicoBinding;
import com.example.smartapp.viewmodels.AgendarServicoViewModel;

import java.time.LocalDate;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AgendarServicoFragment extends Fragment {

    private AgendarServicoBinding binding;
    private AgendarServicoViewModel viewModel;
    private NavController navController;


    private ProfissionalResponse profissionalSelected;
    private ScheduleResponse scheduleResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AgendarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AgendarServicoViewModel.class);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        AgendarServicoFragmentArgs args = AgendarServicoFragmentArgs.fromBundle(getArguments());

        profissionalSelected = args.getPro();
        scheduleResponse = args.getSchedule();

        setupObservers();
        setupDatePicker();
        setupListeners();

        setupView();
    }

    private void setupView() {
        if (profissionalSelected != null) {
            viewModel.loadServicos(profissionalSelected.getId_profissional());
            binding.edtnomeagend.setText(profissionalSelected.getName());
        }
    }

    private void setupSpinners(List<ServicoResponse> servicos) {
        ArrayAdapter<ServicoResponse> servicosAdapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.spinner_item,
                servicos
        );
        servicosAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.servicosSpinner.setAdapter(servicosAdapter);

        if (scheduleResponse != null) {
            int index = -1;
            for (int i = 0; i < servicos.size(); i++) {
                if (servicos.get(i).getId() == scheduleResponse.getServico().getId()) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                binding.servicosSpinner.setSelection(index);
                binding.servicosSpinner.setEnabled(false);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupDatePicker() {
        LocalDate today = LocalDate.now();
        binding.edtpickerdata.setMinDate(System.currentTimeMillis() - 1000);
        LocalDate selectedDate = today;
        if (scheduleResponse != null){
            selectedDate = scheduleResponse.getHour().getInitHour().toLocalDate();
            viewModel.loadHours(profissionalSelected.getId_profissional(), selectedDate);
        }

        binding.edtpickerdata.init(
                selectedDate.getYear(),
                selectedDate.getMonthValue() - 1,
                selectedDate.getDayOfMonth(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    LocalDate date = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
                    viewModel.loadHours(profissionalSelected.getId_profissional(), date);
                }
        );
    }

    private void setupHourPicker(List<HourResponse> hours) {
        if (hours.isEmpty()) {
            binding.errorHours.setVisibility(VISIBLE);
            binding.horarioSpinner.setVisibility(GONE);
        } else {
            ArrayAdapter<HourResponse> horariosAdapter = new ArrayAdapter<>(
                    requireContext(),
                    R.layout.spinner_item,
                    hours
            );
            horariosAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            binding.horarioSpinner.setAdapter(horariosAdapter);

            binding.errorHours.setVisibility(GONE);
            binding.horarioSpinner.setVisibility(VISIBLE);
        }
        atualizarResumo();
    }

    private void setupListeners() {
        binding.btnvoltaragendconfirma.setOnClickListener(v -> {
            navController.popBackStack();
        });

        binding.btnconfirmarAgendamento2.setOnClickListener(v -> {
            if (validarFormulario()) {
                if (scheduleResponse != null){
                    viewModel.updateSchedule(
                            scheduleResponse,
                            (HourResponse) binding.horarioSpinner.getSelectedItem()
                    );
                } else {
                    viewModel.saveSchedule(
                            profissionalSelected.getId_profissional(),
                            (ServicoResponse) binding.servicosSpinner.getSelectedItem(),
                            (HourResponse) binding.horarioSpinner.getSelectedItem()
                    );
                }
            } else {
                Toast.makeText(requireContext(), "Selecione o horário desejado", Toast.LENGTH_SHORT).show();
            }
        });

        binding.servicosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atualizarResumo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.horarioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atualizarResumo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupObservers() {
        viewModel.servicos.observe(getViewLifecycleOwner(), services -> {
            setupSpinners(services);
            atualizarResumo();
        });
        viewModel.hours.observe(getViewLifecycleOwner(), this::setupHourPicker);

        viewModel.added.observe(getViewLifecycleOwner(), response -> {
            if (response) {
                Toast.makeText(getContext(), "Agendado com sucesso", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        });

        viewModel.upgraded.observe(getViewLifecycleOwner(), response -> {
            if (response) {
                Toast.makeText(getContext(), "Reagendado com sucesso", Toast.LENGTH_SHORT).show();
                navController.popBackStack(R.id.gerenciar_agendamento_fragment, false);
            }
        });
    }

    private boolean validarFormulario() {
        return
                binding.servicosSpinner.getSelectedItem() != null &&
                        binding.horarioSpinner.getSelectedItem() != null &&
                        profissionalSelected != null
                ;
    }

    private void atualizarResumo() {
        if (binding.servicosSpinner.getSelectedItem() != null) {
            ServicoResponse servicoSelecionado = (ServicoResponse) binding.servicosSpinner.getSelectedItem();
            binding.edtagendaServiconome.setText(servicoSelecionado.toString());

            double preco = servicoSelecionado.getPrice();
            binding.edtagendaPreco.setText(String.format("R$ %.2f", preco));

            int duration = servicoSelecionado.getDuration();
            binding.summaryDuration.setText(duration + "min");
        }

        String dataSelecionada = String.format("%02d/%02d/%d",
                binding.edtpickerdata.getDayOfMonth(),
                binding.edtpickerdata.getMonth() + 1,
                binding.edtpickerdata.getYear());
        binding.summaryDate.setText(dataSelecionada);

        if (binding.horarioSpinner.getSelectedItem() != null) {
            String horarioSelecionado = binding.horarioSpinner.getSelectedItem().toString();
            binding.summaryTime.setText(horarioSelecionado);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}