package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    @Inject
    public LoginViewModel() {
    }

    public Boolean validateFieds(){
        return true;
    }
}
