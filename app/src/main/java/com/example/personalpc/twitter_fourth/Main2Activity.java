package com.example.personalpc.twitter_fourth;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends ListActivity {


    long maxId;
    UserTimeline userTimeline;

    final Callback<Tweet> actionCallback =new Callback<Tweet>() {
        @Override
        public void success(Result<Tweet> result) {

        }

        @Override
        public void failure(TwitterException exception) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timeline);


        getTimeline();
      /*  collectionTimeLine();*/


    }

    public void getTimeline(){

        userTimeline = new UserTimeline.Builder()
                .screenName("realDonaldTrump")
                .build();


        //adding the call back
        userTimeline.next(null, callback);


        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);


    }

    public void gettingAllUserTimeline(){
/*
        ArrayList<Tweet> tweets = new ArrayList<>();

       UserTimeline secondUserTimeline = new UserTimeline.Builder()
        TwitterCore.getInstance().getApiClient(session).getStatusesService()
                .userTimeline(null,
                        "screenname",
                        10 //the number of tweets we want to fetch,
                null,
                null,
                null,
                null,
                null,
                null,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        for (Tweet t : result.data) {
                            tweets.add(t);
                            android.util.Log.d("twittercommunity", "tweet is " + t.text);
                        }
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        android.util.Log.d("twittercommunity", "exception " + exception);
                    }
                });*/





    }


    public void collectionTimeLine(){

        final CollectionTimeline timeline = new CollectionTimeline.Builder()
                .id(539487832448843776L)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(timeline)
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .setOnActionCallback(actionCallback)
                .build();
        setListAdapter(adapter);


    }

    //This function will help to get data one by one

    Callback<TimelineResult<Tweet>> callback = new Callback<TimelineResult<Tweet>>()
    {
        @Override
        public void success(Result<TimelineResult<Tweet>> searchResult)
        {
            List<Tweet> tweets = searchResult.data.items;

            for (Tweet tweet : tweets)
            {
                String str = tweet.text; //Here is the body
                maxId = tweet.id;
            }
            if (searchResult.data.items.size() == 200)
                userTimeline.previous(maxId,  callback);
          /*  else
                //closeOutputFile();*/

        }
        @Override
        public void failure(TwitterException error)
        {
            //Do something
        }
    };


}
