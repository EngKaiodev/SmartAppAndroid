package com.example.smartapp.activities;

import static android.view.View.VISIBLE;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartapp.core.ErroHelper;
import com.example.smartapp.core.Preferences;
import com.example.smartapp.databinding.LoginActivityBinding;
import com.example.smartapp.domain.models.ErrorModel;
import com.example.smartapp.viewmodels.LoginViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private LoginActivityBinding binding;
    private LoginViewModel loginViewModel;
    @Inject
    Preferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isLogged = preferences.isLogged();

        if (isLogged) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            binding = LoginActivityBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

            setupListeners();
        }
    }

    private void setupListeners() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginViewModel.validateFieds(
                        binding.edtEmail.getText().toString(),
                        binding.edtPassword.getText().toString()
                )) {
                    Disposable disposable = loginViewModel.tryLogin(
                                    binding.edtEmail.getText().toString(),
                                    binding.edtPassword.getText().toString()
                            )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> {
                        if (response.isSuccessful() && response.code() == 200) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            ErrorModel error = ErroHelper.getErrorModel(response);
                            binding.tvError.setText(error.getMessage());
                            binding.erroContainer.setVisibility(View.VISIBLE);
                        }
                    }, throwable -> {
                        Toast.makeText(LoginActivity.this, "Erro ao registrar", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    binding.erroContainer.setVisibility(VISIBLE);
                }
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.esqueceusenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });
    }
}
