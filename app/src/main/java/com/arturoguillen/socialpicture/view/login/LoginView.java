package com.arturoguillen.socialpicture.view.login;

import com.arturoguillen.socialpicture.entities.client.LoginRequest;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public interface LoginView {

    void loginOK(LoginRequest loginRequest);

    void loginNOK(String s);
}
