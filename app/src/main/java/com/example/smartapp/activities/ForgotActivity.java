package com.example.smartapp.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartapp.databinding.ActivityForgotBinding;

public class ForgotActivity extends AppCompatActivity {

    private ActivityForgotBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
