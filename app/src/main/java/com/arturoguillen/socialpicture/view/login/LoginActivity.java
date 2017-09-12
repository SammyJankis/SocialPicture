package com.arturoguillen.socialpicture.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arturoguillen.socialpicture.R;
import com.arturoguillen.socialpicture.di.component.ActivityComponent;
import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.arturoguillen.socialpicture.presenter.LoginPresenter;
import com.arturoguillen.socialpicture.view.InjectedActivity;
import com.arturoguillen.socialpicture.view.search.SearchActivity;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends InjectedActivity implements LoginView {

    @BindView(R.id.twitter_login_button)
    TwitterLoginButton twitterLoginButton;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attachView(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter.twitter(twitterLoginButton);
    }

    @Override
    protected void injectComponent(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loginOK(LoginRequest loginRequest) {
        startActivity(SearchActivity.createIntent(this, loginRequest));
        finish();
    }

    @Override
    public void loginNOK(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
