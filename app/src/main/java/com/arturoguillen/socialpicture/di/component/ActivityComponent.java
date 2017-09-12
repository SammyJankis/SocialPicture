package com.arturoguillen.socialpicture.di.component;

import com.arturoguillen.socialpicture.di.module.SharedPreferencesModule;
import com.arturoguillen.socialpicture.view.login.LoginActivity;
import com.arturoguillen.socialpicture.view.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

@Singleton
@Component(modules = {SharedPreferencesModule.class})
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(SearchActivity searchActivity);
}
