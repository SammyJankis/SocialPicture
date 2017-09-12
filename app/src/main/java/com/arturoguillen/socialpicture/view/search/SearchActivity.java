package com.arturoguillen.socialpicture.view.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arturoguillen.socialpicture.R;
import com.arturoguillen.socialpicture.di.component.ActivityComponent;
import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.arturoguillen.socialpicture.presenter.SearchPresenter;
import com.arturoguillen.socialpicture.view.InjectedActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class SearchActivity extends InjectedActivity implements SearchView {

    private static final String EXTRA_LOGIN_REQUEST = "EXTRA_LOGIN_REQUEST";

    @Inject
    SearchPresenter presenter;

    public static Intent createIntent(Context context, LoginRequest loginRequest) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_LOGIN_REQUEST, loginRequest);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
        setContentView(R.layout.activity_search);

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
    public void searchOK(List<String> imagesUrls) {

    }

    @Override
    public void searchNOK(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
