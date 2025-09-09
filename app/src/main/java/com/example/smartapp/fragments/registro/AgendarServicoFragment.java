package com.example.smartapp.fragments.registro;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.databinding.AgendarServicoBinding;
import com.example.smartapp.viewmodels.AgendarServicoViewModel;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AgendarServicoFragment extends Fragment {

    private AgendarServicoBinding binding;
    private AgendarServicoViewModel viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AgendarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AgendarServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        setupSpinners();
        setupDatePicker();
        setupListeners();
        setupObservers();
    }

    private void setupSpinners() {
        // Configurar spinner de serviços
        List<String> servicos = Arrays.asList(
                "Selecione um serviço",
                "Corte de Cabelo",
                "Coloração",
                "Manicure",
                "Pedicure",
                "Massagem",
                "Limpeza de Pele"
        );

        ArrayAdapter<String> servicosAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                servicos
        );
        servicosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.servicosSpinner.setAdapter(servicosAdapter);

        // Configurar spinner de horários
        List<String> horarios = Arrays.asList(
                "Selecione um horário",
                "08:00", "09:00", "10:00", "11:00",
                "13:00", "14:00", "15:00", "16:00", "17:00"
        );

        ArrayAdapter<String> horariosAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                horarios
        );
        horariosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.horarioSpinner.setAdapter(horariosAdapter);
    }

    private void setupDatePicker() {
        // Configurar data mínima como hoje
        Calendar calendar = Calendar.getInstance();
        binding.edtpickerdata.setMinDate(calendar.getTimeInMillis());

        // Configurar data máxima como 60 dias a partir de hoje
        calendar.add(Calendar.DAY_OF_MONTH, 60);
        binding.edtpickerdata.setMaxDate(calendar.getTimeInMillis());
    }

    private void setupListeners() {
        binding.btnvoltaragendconfirma.setOnClickListener(v -> {
            navController.popBackStack();
        });

        binding.btnconfirmarAgendamento2.setOnClickListener(v -> {
            if (validarFormulario()) {
                navController.navigate(AgendarServicoFragmentDirections.confirmaragendamentofragment());
            }
        });

        // Atualizar resumo quando os dados mudarem
        binding.servicosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atualizarResumo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.edtpickerdata.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
                atualizarResumo();
            });
        }

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
        // Observar mudanças no ViewModel se necessário
    }

    private boolean validarFormulario() {
        boolean valido = true;

        // Validar serviço
        if (binding.servicosSpinner.getSelectedItemPosition() == 0) {
            binding.serviceError.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            binding.serviceError.setVisibility(View.GONE);
        }

        // Validar data (sempre terá uma data selecionada devido ao DatePicker)
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(
                binding.edtpickerdata.getYear(),
                binding.edtpickerdata.getMonth(),
                binding.edtpickerdata.getDayOfMonth()
        );

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        if (selectedDate.before(today)) {
            binding.dateError.setText("Por favor, selecione uma data futura.");
            binding.dateError.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            binding.dateError.setVisibility(View.GONE);
        }

        // Validar horário
        if (binding.horarioSpinner.getSelectedItemPosition() == 0) {
            binding.timeError.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            binding.timeError.setVisibility(View.GONE);
        }

        if (!valido) {
            Toast.makeText(requireContext(), "Por favor, preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
        }

        return valido;
    }

    private void atualizarResumo() {
        // Atualizar serviço no resumo
        if (binding.servicosSpinner.getSelectedItemPosition() > 0) {
            String servicoSelecionado = binding.servicosSpinner.getSelectedItem().toString();
            binding.edtagendaServiconome.setText(servicoSelecionado);

            // Atualizar preço baseado no serviço (exemplo)
            double preco = obterPrecoServico(servicoSelecionado);
            binding.edtagendaPreco.setText(String.format("R$ %.2f", preco));
        }

        // Atualizar data no resumo
        String dataSelecionada = String.format("%02d/%02d/%d",
                binding.edtpickerdata.getDayOfMonth(),
                binding.edtpickerdata.getMonth() + 1, // Month é 0-based
                binding.edtpickerdata.getYear());
        binding.summaryDate.setText(dataSelecionada);

        // Atualizar horário no resumo
        if (binding.horarioSpinner.getSelectedItemPosition() > 0) {
            String horarioSelecionado = binding.horarioSpinner.getSelectedItem().toString();
            binding.summaryTime.setText(horarioSelecionado);
        }
    }

    private double obterPrecoServico(String servico) {
        // Lógica para obter preço baseado no serviço (exemplo)
        switch (servico) {
            case "Corte de Cabelo": return 30.00;
            case "Coloração": return 80.00;
            case "Manicure": return 25.00;
            case "Pedicure": return 30.00;
            case "Massagem": return 70.00;
            case "Limpeza de Pele": return 60.00;
            default: return 0.00;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}