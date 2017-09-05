package com.arturoguillen.socialpicture.presenter;

import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.arturoguillen.socialpicture.model.LoginModel;
import com.arturoguillen.socialpicture.model.Preferences;
import com.arturoguillen.socialpicture.view.login.LoginView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import javax.inject.Inject;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class LoginPresenter implements PresenterInterface<LoginView> {

    private LoginView view;

    @Inject
    LoginModel loginModel;

    @Inject
    Preferences preferences;

    @Inject
    public LoginPresenter(LoginModel loginModel, Preferences preferences) {
        this.loginModel = loginModel;
        this.preferences = preferences;
    }

    @Override
    public void attachView(LoginView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void twitter(TwitterLoginButton twitterLoginButton) {
        LoginRequest loginRequest = preferences.getLoginData() ;
        if (preferences.getLoginData()!=null) {
            view.loginOK(loginRequest);
        } else {
            twitterLoginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    if (view != null) {
                        LoginRequest loginRequest = loginModel.providesLoginRequest(result);
                        preferences.putLoginData(loginRequest);
                        view.loginOK(loginRequest);
                    }
                }

                @Override
                public void failure(TwitterException exception) {
                    if (view != null)
                        view.loginNOK(exception.getMessage());
                }
            });
        }
    }
}
