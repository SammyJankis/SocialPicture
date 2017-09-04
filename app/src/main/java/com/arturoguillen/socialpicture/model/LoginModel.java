package com.arturoguillen.socialpicture.model;

import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class LoginModel {

    public LoginRequest providesLoginRequest(Result<TwitterSession> result) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAuthType(LoginRequest.Type.TWITTER);
        loginRequest.setAuthToken(result.data.getAuthToken().token);
        loginRequest.setAuthSecret(result.data.getAuthToken().secret);
        loginRequest.setAuthId(String.valueOf(result.data.getUserId()));

        return loginRequest;
    }
}
