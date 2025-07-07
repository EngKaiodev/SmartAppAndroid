package com.example.smartapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartapp.databinding.LoginActivityBinding;
import com.example.smartapp.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private LoginActivityBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        setupListeners();
    }

    private void setupListeners() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginViewModel.validateFieds()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
