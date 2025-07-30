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

import com.example.smartapp.adapters.CategoryAdapter;
import com.example.smartapp.adapters.ProfissionalAdapter;
import com.example.smartapp.databinding.DashboardBinding;
import com.example.smartapp.domain.models.CategoryModel;
import com.example.smartapp.domain.models.ProfissionalModel;
import com.example.smartapp.viewmodels.DashboardViewModel;

import com.example.smartapp.fragments.registro.DashboardFragmentDirections;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends Fragment {
    private DashboardBinding binding;
    private DashboardViewModel viewModel;
    private NavController navController;

    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> categoryList;

    private List<ProfissionalModel> profissionalList;
    private ProfissionalAdapter profissionalAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DashboardBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(this); // 2 INSTACIA NAV CONTROLER

        mockedList();
        setupListeners();
        setupAdapters();
    }
    //Lista de exemplos para testar
    private void mockedList() {
        categoryList = List.of(
                new CategoryModel(1, "Cabelereiro"),
                new CategoryModel(2, "Estético"),
                new CategoryModel(3, "Personal"),
                new CategoryModel(4, "Manicure"),
                new CategoryModel(5, "Pedicure"),
                new CategoryModel(6, "Massagem"),
                new CategoryModel(7, "Depilação"),
                new CategoryModel(8, "Maquiagem"),
                new CategoryModel(9, "Barbearia")
        );

        profissionalList = List.of(
                new ProfissionalModel(1, "joao tonho", "cabelereiro","5")

        );
    }

    private void setupAdapters() {
        categoryAdapter = new CategoryAdapter(categoryList);

        binding.rvCategorias.setAdapter(categoryAdapter);
        binding.rvCategorias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        profissionalAdapter = new ProfissionalAdapter(profissionalList);
        binding.rvCatProfissional.setAdapter(profissionalAdapter);
        binding.rvCatProfissional.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    private void setupListeners() {
        binding.btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(DashboardFragmentDirections.actionHomeToPerfilPrestador());
            }
        });

        binding.btnAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(DashboardFragmentDirections.actionHomeToAgendamentos());
            }
        });

       binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               navController.navigate(DashboardFragmentDirections.actionHomeToCriarProduto());
           }
       });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
