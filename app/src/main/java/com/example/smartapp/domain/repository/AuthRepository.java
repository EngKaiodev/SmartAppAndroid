package com.example.smartapp.domain.repository;

import android.util.Log;

import com.example.smartapp.core.ErroHelper;
import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.LoginRequest;
import com.example.smartapp.core.domain.request.UserRequest;
import com.example.smartapp.core.domain.response.UserResponse;
import com.example.smartapp.core.network.service.AuthService;

import org.json.JSONObject;

import java.io.Console;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

@Singleton
public class AuthRepository {

    private final AuthService authService;
    private final Preferences preferences;

    @Inject
    public AuthRepository(AuthService authService, Preferences preferences) {
        this.authService = authService;
        this.preferences = preferences;
    }

    public Single<Response<UserResponse>> register(UserRequest userRequest) {
        return authService.register(userRequest).doOnSuccess(response -> {
            if (response.isSuccessful() && response.body() != null) {
                preferences.createSession(response.body());
            }
        }).doOnError(error -> {
            Log.e("AuthRepository", "Erro ao registrar: " + error);
        });
    }

    public Single<Response<UserResponse>> login (LoginRequest request){
        return authService.login(request)
                .doOnSuccess(response -> {
                    if (response.isSuccessful() && response.body() != null) {
                        preferences.createSession(response.body());
                    }
                })
                .doOnError(error -> {
                    Log.e("AuthRepository", "Erro ao logar: " + error);
                });
    }
}
