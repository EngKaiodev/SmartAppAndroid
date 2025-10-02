package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.CalendarConstraints;
import com.example.smartapp.viewmodels.CalendarioViewmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Fragment simples que abre um MaterialDatePicker e exibe os slots disponíveis
 * para a data selecionada com base na configuração salva em SharedPreferences.
 *
 * Não exige layout XML (gera a UI em código) para facilitar colagem direta.
 */
public class CalendarioFragment extends Fragment {

    private CalendarioViewmodel viewModel;

    // Views
    private TextView tvSelectedDate;
    private ListView lvSlots;
    private ArrayAdapter<String> slotsAdapter;

    public CalendarioFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull android.view.LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Cria layout simples programaticamente para evitar dependência de XML
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int padding = (int) (16 * requireContext().getResources().getDisplayMetrics().density);
        root.setPadding(padding, padding, padding, padding);

        Button btnSelectDate = new Button(requireContext());
        btnSelectDate.setText("Selecionar data");
        root.addView(btnSelectDate, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tvSelectedDate = new TextView(requireContext());
        tvSelectedDate.setTextSize(16f);
        tvSelectedDate.setGravity(Gravity.START);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvParams.topMargin = padding / 2;
        root.addView(tvSelectedDate, tvParams);

        lvSlots = new ListView(requireContext());
        LinearLayout.LayoutParams lvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        lvParams.weight = 1;
        lvParams.topMargin = padding / 2;
        root.addView(lvSlots, lvParams);

        // Adapter inicial vazio
        slotsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        lvSlots.setAdapter(slotsAdapter);

        // ViewModel
        viewModel = new ViewModelProvider(this).get(CalendarioViewmodel.class);

        // Observers
        viewModel.getAvailableSlots().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> slots) {
                slotsAdapter.clear();
                if (slots != null && !slots.isEmpty()) {
                    slotsAdapter.addAll(slots);
                }
                slotsAdapter.notifyDataSetChanged();
            }
        });

        // Botão abre o MaterialDatePicker
        btnSelectDate.setOnClickListener(v -> {
            // opcional: criar constraints (aqui sem validação direta para deixar o usuário escolher)
            CalendarConstraints constraints = new CalendarConstraints.Builder().build();

            MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Selecione a data")
                    .setCalendarConstraints(constraints)
                    .build();

            picker.addOnPositiveButtonClickListener(selection -> {
                long selectedMillis = selection;
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(selectedMillis);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH) + 1;
                int year = c.get(Calendar.YEAR);
                String dateLabel = String.format("%02d/%02d/%d", day, month, year);
                tvSelectedDate.setText("Data selecionada: " + dateLabel);

                // Verifica se o dia é permitido
                if (!viewModel.isDateAllowed(selectedMillis)) {
                    Toast.makeText(requireContext(), "Data indisponível. Escolha uma data em um dia de funcionamento.", Toast.LENGTH_LONG).show();
                    viewModel.clearAvailableSlots();
                    return;
                }

                // Gera slots (assíncrono no ViewModel). Resultado será publicado em getAvailableSlots().
                viewModel.generateSlotsForDate(selectedMillis);
            });

            picker.show(getParentFragmentManager(), "DATE_PICKER_CALENDARIO");
        });

        return root;
    }
}
