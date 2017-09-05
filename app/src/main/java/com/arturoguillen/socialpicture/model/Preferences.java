package com.arturoguillen.socialpicture.model;

import android.content.SharedPreferences;

import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by arturo.guillen on 05/09/2017.
 */

public class Preferences {

    private static final String PREFERENCE_LOGIN = "PREFERENCE_LOGIN";

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Gson gson;

    @Inject
    public Preferences(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void putLoginData(LoginRequest loginRequest) {
        String json = gson.toJson(loginRequest);
        sharedPreferences.edit().putString(PREFERENCE_LOGIN, json).apply();
    }

    public LoginRequest getLoginData() {
        String json = sharedPreferences.getString(PREFERENCE_LOGIN, "");
        return gson.fromJson(json, LoginRequest.class);
    }
}
