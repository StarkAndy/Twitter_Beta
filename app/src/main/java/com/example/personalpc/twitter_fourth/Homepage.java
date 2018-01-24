package com.example.personalpc.twitter_fourth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.UserTimeline;

import okhttp3.OkHttpClient;
import retrofit2.Call;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<Tweet> call = statusesService.show(524971209851543553L, null, null, null);
        call.enqueue(new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                //Do something with result
            }

            public void failure(TwitterException exception) {
                //Do something on failure
            }
        });
    }

    public void request(){



        UserTimeline userTimeline = new UserTimeline.Builder().screenName("twitterdev").build();

    }

}
