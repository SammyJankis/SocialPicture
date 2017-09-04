package com.arturoguillen.socialpicture.presenter;

import com.arturoguillen.socialpicture.model.LoginModel;
import com.arturoguillen.socialpicture.view.login.LoginView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class LoginPresenter implements PresenterInterface<LoginView> {

    private LoginView view;
    private LoginModel loginModel;

    public LoginPresenter(LoginModel loginModel) {
        this.loginModel = loginModel;
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
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                if (view != null)
                    view.loginOK(loginModel.providesLoginRequest(result));
            }

            @Override
            public void failure(TwitterException exception) {
                if (view != null)
                    view.loginNOK("Error al loguearse en Twitter");
            }
        });

    }
}
