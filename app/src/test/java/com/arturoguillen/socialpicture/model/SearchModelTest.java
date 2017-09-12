package com.arturoguillen.socialpicture.model;

import android.os.Build;

import com.arturoguillen.socialpicture.BuildConfig;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.TweetEntities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arturo.guillen on 12/09/2017.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class SearchModelTest {

    @Test
    public void addMediaUrl_from_nullMediaEntity() throws Exception {
        SearchModel searchModel = new SearchModel();
        List<String> list = new ArrayList<>();

        searchModel.addMediaUrlFromMediaEntity(list, null);

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void addMediaUrl_from_mediaEntityWithNullMediaUrl() throws Exception {
        SearchModel searchModel = new SearchModel();
        List<String> list = new ArrayList<>();
        MediaEntity mediaEntity = new MediaEntity(null, null, null, 0, 0, 0, null, null, null, null, 0, null, null, null, null);

        searchModel.addMediaUrlFromMediaEntity(list, mediaEntity);

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void addMediaEntity_from_nullTweetEntity() throws Exception {
        SearchModel searchModel = new SearchModel();
        List<String> list = new ArrayList<>();

        searchModel.addMediaEntityFromTweetEntity(list, null);

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void addMediaEntity_from_tweetEntityWithNullMedia() throws Exception {
        SearchModel searchModel = new SearchModel();
        List<String> list = new ArrayList<>();
        TweetEntities tweetEntity = new TweetEntities(null, null, null, null, null);

        searchModel.addMediaEntityFromTweetEntity(list, tweetEntity);

        Assert.assertTrue(list.isEmpty());
    }


}
