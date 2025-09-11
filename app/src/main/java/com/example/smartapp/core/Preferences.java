package com.example.smartapp.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartapp.core.domain.response.UserResponse;

import javax.inject.Inject;

public class Preferences {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "MyPrefs";

    @Inject
    public Preferences(Context context){
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    private static final String KEY_TOKEN = "TOKEN";
    private static final String IS_LOGGIN = "IS_LOGGIN";
    private static final String KEY_TYPE = "TYPE";

    public void createSession (UserResponse response){
        editor.putBoolean(IS_LOGGIN, true);
        editor.putInt(KEY_TYPE, response.getType());
        editor.commit();
        editor.apply();
    }

    public void insertToken (String token){
        editor.putString(KEY_TOKEN, token);
        editor.commit();
        editor.apply();
    }

    public void logout(){
        editor.clear();
        editor.commit();
        editor.apply();
    }

    public boolean isLogged(){
        return preferences.getBoolean(IS_LOGGIN, false);
    }

    public String getToken(){
        return preferences.getString(KEY_TOKEN, "Sem token");
    }

    public int getType(){
        return preferences.getInt(KEY_TYPE, -1);
    }
}
