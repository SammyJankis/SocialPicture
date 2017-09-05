package com.arturoguillen.socialpicture.view.feed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.services.SearchService;

import retrofit2.Call;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class SearchActivity extends AppCompatActivity {

    private static final String EXTRA_LOGIN_REQUEST = "EXTRA_LOGIN_REQUEST";

    public static Intent createIntent(Context context, LoginRequest loginRequest) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_LOGIN_REQUEST, loginRequest);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();

        SearchService searchService = twitterApiClient.getSearchService();

        Call<Search> call = searchService.tweets("@ladygaga", null, null, null, null, null, null, null, null, null);
        call.enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                Log.i("MIERDA", result.toString());
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });

    }
}
