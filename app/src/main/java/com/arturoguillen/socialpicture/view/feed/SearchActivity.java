package com.arturoguillen.socialpicture.view.feed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.arturoguillen.socialpicture.entities.client.LoginRequest;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class SearchActivity extends AppCompatActivity {

    private static final String EXTRA_LOGIN_REQUEST = "EXTRA_LOGIN_REQUEST";

    public static Intent createIntent(Context context, LoginRequest loginRequest) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_LOGIN_REQUEST, loginRequest);
        return intent;
    }
}