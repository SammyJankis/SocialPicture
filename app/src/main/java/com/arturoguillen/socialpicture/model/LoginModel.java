package com.arturoguillen.socialpicture.model;

import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.facebook.login.LoginResult;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterSession;

import javax.inject.Inject;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class LoginModel {

    @Inject
    public LoginModel() {
    }

    public LoginRequest providesLoginRequest(Result<TwitterSession> result) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAuthType(LoginRequest.Type.TWITTER);
        loginRequest.setAuthToken(result.data.getAuthToken().token);
        return loginRequest;
    }

    public LoginRequest providesLoginRequest(LoginResult result) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAuthType(LoginRequest.Type.FACEBOOK);
        loginRequest.setAuthToken(result.getAccessToken().getToken());

        return loginRequest;
    }
}
