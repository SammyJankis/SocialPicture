package com.arturoguillen.socialpicture.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arturoguillen.socialpicture.App;
import com.arturoguillen.socialpicture.di.ActivityComponent;


/**
 * Created by arturo.guillen on 04/09/2017.
 */

public abstract class InjectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(((App) getApplication())
                .getComponent());
    }

    protected abstract void injectComponent(ActivityComponent component);

}
