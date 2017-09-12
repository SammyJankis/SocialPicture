package com.arturoguillen.socialpicture.model;

import android.text.TextUtils;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetEntities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by arturo.guillen on 12/09/2017.
 */

public class SearchModel {

    @Inject
    public SearchModel() {
    }

    public List<String> providesImagesUrls(Result<Search> result) {
        List<String> list = new ArrayList<>();
        for (Tweet tweet : result.data.tweets) {
            addMediaEntityFromTweetEntity(list, tweet.entities);
        }
        return list;
    }

    protected void addMediaEntityFromTweetEntity(List<String> list, TweetEntities entities) {
        if (entities != null && entities.media != null && !entities.media.isEmpty()) {
            for (MediaEntity mediaEntity : entities.media) {
                addMediaUrlFromMediaEntity(list, mediaEntity);
            }
        }
    }

    protected void addMediaUrlFromMediaEntity(List<String> list, MediaEntity mediaEntity) {
        if (mediaEntity != null && !TextUtils.isEmpty(mediaEntity.mediaUrl)) {
            list.add(mediaEntity.mediaUrl);
        }
    }
}


