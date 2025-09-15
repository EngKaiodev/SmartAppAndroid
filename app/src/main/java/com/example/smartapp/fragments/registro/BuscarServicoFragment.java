package com.example.smartapp.fragments.registro;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartapp.adapters.HistoricoAdapter;
import com.example.smartapp.databinding.BuscarServicoBinding;
import com.example.smartapp.domain.models.HistoricoModel;
import com.example.smartapp.viewmodels.BuscarServicoViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BuscarServicoFragment extends Fragment {

    private BuscarServicoBinding binding;
    private BuscarServicoViewModel viewModel;
    private NavController navController;

    private HistoricoAdapter historicoAdapter;
    private List<HistoricoModel> historicoList;
    private List<HistoricoModel> filteredList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BuscarServicoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(BuscarServicoViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        mockedList();
        setupSpinners();
        setupAdapters();
        setupListeners();
        setupObservers();
    }

    private void mockedList() {
        historicoList = Arrays.asList(
                new HistoricoModel(1, "João Cabeleireiro", "Corte de Cabelo", "11/05/2025", "R$ 30,00", "5"),
                new HistoricoModel(2, "Maria Beauty", "Coloração", "12/05/2025", "R$ 80,00", "4"),
                new HistoricoModel(3, "Ana Estética", "Limpeza de Pele", "13/05/2025", "R$ 60,00", "5"),
                new HistoricoModel(4, "Clara Manicure", "Manicure", "14/05/2025", "R$ 25,00", "4"),
                new HistoricoModel(5, "Pedro Massagista", "Massagem", "15/05/2025", "R$ 70,00", "5"),
                new HistoricoModel(6, "Carlos Barbeiro", "Barba", "16/05/2025", "R$ 20,00", "3")
        );
        filteredList = new ArrayList<>(historicoList);
    }

    private void setupSpinners() {
        // Configurar spinner de categorias
        List<String> categories = Arrays.asList(
                "Todas as categorias",
                "Corte de Cabelo",
                "Coloração",
                "Manicure",
                "Pedicure",
                "Massagem",
                "Limpeza de Pele",
                "Barba"
        );

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                categories
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.categoryFilter.setAdapter(categoryAdapter);

        // Configurar spinner de avaliações
        List<String> ratings = Arrays.asList(
                "Todas as avaliações",
                "5 estrelas",
                "4 estrelas ou mais",
                "3 estrelas ou mais",
                "2 estrelas ou mais",
                "1 estrela ou mais"
        );

        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                ratings
        );
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ratingFilter.setAdapter(ratingAdapter);
    }

    private void setupAdapters() {
        historicoAdapter = new HistoricoAdapter(filteredList, historicoModel -> {
            // Navegar para a tela do prestador com os dados do histórico selecionado
            navController.navigate(BuscarServicoFragmentDirections.actionIrPrestador());
        });

        binding.rvtlistaservico.setAdapter(historicoAdapter);
        binding.rvtlistaservico.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupListeners() {
        binding.btnvoltarbs.setOnClickListener(v -> navController.popBackStack());

        // Buscar ao clicar no botão de busca
        binding.btnSearch.setOnClickListener(v -> aplicarFiltros());

        // Buscar ao pressionar Enter no teclado
        binding.searchQuery.setOnEditorActionListener((v, actionId, event) -> {
            aplicarFiltros();
            return true;
        });

        // Buscar ao digitar (busca em tempo real)
        binding.searchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 2) {
                    aplicarFiltros();
                } else if (s.length() == 0) {
                    aplicarFiltros();
                }
            }
        });

        // Aplicar filtros ao selecionar opções nos spinners
        binding.categoryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aplicarFiltros();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.ratingFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aplicarFiltros();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Aplicar filtros ao clicar no botão
        binding.applyFiltersBtn.setOnClickListener(v -> aplicarFiltros());

        // Limpar filtros
        binding.clearFiltersBtn.setOnClickListener(v -> limparFiltros());
    }

    private void setupObservers() {
        // Observar mudanças no ViewModel se necessário
    }

    private void aplicarFiltros() {
        String query = binding.searchQuery.getText().toString().trim().toLowerCase(Locale.ROOT);
        String category = binding.categoryFilter.getSelectedItem().toString();
        String location = binding.locationFilter.getText().toString().trim().toLowerCase(Locale.ROOT);
        String ratingFilter = binding.ratingFilter.getSelectedItem().toString();

        filteredList.clear();

        for (HistoricoModel item : historicoList) {
            boolean matchesQuery = query.isEmpty() ||
                    item.getName().toLowerCase(Locale.ROOT).contains(query) ||
                    item.getServico().toLowerCase(Locale.ROOT).contains(query);

            boolean matchesCategory = category.equals("Todas as categorias") ||
                    item.getServico().equalsIgnoreCase(category);

            boolean matchesLocation = location.isEmpty() ||
                    item.getName().toLowerCase(Locale.ROOT).contains(location);

            boolean matchesRating = true;
            if (!ratingFilter.equals("Todas as avaliações")) {
                int minRating = Integer.parseInt(ratingFilter.substring(0, 1));
                int itemRating = Integer.parseInt(item.getAvaliacao());
                matchesRating = itemRating >= minRating;
            }

            if (matchesQuery && matchesCategory && matchesLocation && matchesRating) {
                filteredList.add(item);
            }
        }

        historicoAdapter.notifyDataSetChanged();
        atualizarEstadoVazio();
    }

    private void limparFiltros() {
        binding.searchQuery.setText("");
        binding.categoryFilter.setSelection(0);
        binding.locationFilter.setText("");
        binding.ratingFilter.setSelection(0);

        aplicarFiltros();
    }

    private void atualizarEstadoVazio() {
        if (filteredList.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.rvtlistaservico.setVisibility(View.GONE);
            binding.resultsTitle.setText("Nenhum resultado encontrado");
        } else {
            binding.emptyState.setVisibility(View.GONE);
            binding.rvtlistaservico.setVisibility(View.VISIBLE);
            binding.resultsTitle.setText("Resultados da Busca (" + filteredList.size() + ")");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}