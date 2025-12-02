package com.example.smartapp.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartapp.core.domain.response.UserResponse;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class Preferences {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "MyPrefs";

    @Inject
    public Preferences(@ApplicationContext Context context){
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    private static final String KEY_TOKEN = "TOKEN";
    private static final String KEY_REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String IS_LOGGIN = "IS_LOGGIN";
    private static final String KEY_TYPE = "TYPE";
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";

    public void createSession (UserResponse response){
        editor.putBoolean(IS_LOGGIN, true);
        editor.putInt(KEY_TYPE, response.getType());
        editor.putInt(KEY_ID, response.getId());
        editor.putString(KEY_TOKEN, response.getToken());
        editor.putString(KEY_REFRESH_TOKEN, response.getRefreshToken());
        editor.putString(KEY_NAME, response.getName());
        editor.commit();
        editor.apply();
    }

    public void updateToken (String token){
        editor.putString(KEY_TOKEN, token);
        editor.commit();
        editor.apply();
    }

    public String getRefreshToken(){
        return preferences.getString(KEY_REFRESH_TOKEN, "Sem refresh token");
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

    public String getName(){
        return preferences.getString(KEY_NAME, "Sem nome");
    }

    public void setName(String name){
        editor.putString(KEY_NAME, name);
        editor.commit();
        editor.apply();
    }

    public int getType(){
        return preferences.getInt(KEY_TYPE, -1);
    }

    public int getId(){
        return preferences.getInt(KEY_ID, -1);
    }
}
