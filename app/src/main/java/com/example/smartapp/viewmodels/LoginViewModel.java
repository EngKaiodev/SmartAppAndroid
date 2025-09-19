package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.LoginRequest;
import com.example.smartapp.core.domain.response.UserResponse;
import com.example.smartapp.domain.repository.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Single;
import retrofit2.Response;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final Preferences preferences;
    private final AuthRepository authRepository;
    @Inject
    public LoginViewModel(Preferences preferences, AuthRepository authRepository) {
        this.preferences = preferences;
        this.authRepository = authRepository;
    }

    public Boolean validateFieds( String email, String password){
        return email != null && password != null && !email.isEmpty() && !password.isEmpty();
    }

    public Single<Response<UserResponse>> tryLogin(String email, String password){
        return authRepository.login(
                new LoginRequest(
                    email,
                    password
                )
        );
    }
}
