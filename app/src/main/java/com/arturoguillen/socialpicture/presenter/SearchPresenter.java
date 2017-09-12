package com.arturoguillen.socialpicture.presenter;


import com.arturoguillen.socialpicture.model.SearchModel;
import com.arturoguillen.socialpicture.view.search.SearchView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.services.SearchService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by arturo.guillen on 12/09/2017.
 */

public class SearchPresenter implements PresenterInterface<SearchView> {
    private SearchView view;

    @Inject
    SearchModel searchModel;

    @Inject
    public SearchPresenter(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    @Override
    public void attachView(SearchView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void search(String searchTerm) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        SearchService searchService = twitterApiClient.getSearchService();
        Call<Search> call = searchService.tweets(searchTerm, null, null, null, null, 100, null, null, null, null);
        call.enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                List<String> imagesUrls = searchModel.providesImagesUrls(result);
                view.searchOK(imagesUrls);
            }

            @Override
            public void failure(TwitterException exception) {
                view.searchNOK(exception.getMessage());
            }
        });
    }
}