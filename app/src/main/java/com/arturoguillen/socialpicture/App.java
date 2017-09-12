package com.arturoguillen.socialpicture;

import android.app.Application;
import android.util.Log;

import com.arturoguillen.socialpicture.di.component.ActivityComponent;
import com.arturoguillen.socialpicture.di.component.DaggerActivityComponent;
import com.arturoguillen.socialpicture.di.module.ImageRequestModule;
import com.arturoguillen.socialpicture.di.module.SharedPreferencesModule;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class App extends Application {

    private ActivityComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initTwitterSDK();
        component = createComponent();
    }

    private void initTwitterSDK() {
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.CONSUMER_KEY), getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    protected ActivityComponent createComponent() {
        return DaggerActivityComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .imageRequestModule(new ImageRequestModule(this))
                .build();

    }

    public ActivityComponent getComponent() {
        return component;
    }
}
