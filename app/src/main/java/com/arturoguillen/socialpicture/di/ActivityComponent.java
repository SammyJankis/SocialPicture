package com.arturoguillen.socialpicture.di;

import com.arturoguillen.socialpicture.view.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

@Singleton
@Component(modules = {})
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);

}
