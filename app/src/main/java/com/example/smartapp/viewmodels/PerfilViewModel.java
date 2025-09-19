package com.example.smartapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PerfilViewModel extends ViewModel {

    private final Preferences preferences;

    @Inject
    public PerfilViewModel(Preferences preferences){
        this.preferences = preferences;
    }

    public void doLogout(){
        preferences.logout();
    }
}
