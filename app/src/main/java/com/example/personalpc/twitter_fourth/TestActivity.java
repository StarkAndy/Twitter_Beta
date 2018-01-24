package com.example.personalpc.twitter_fourth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;

    UserTimeline userTimeline = null;

    private   long maxId;


    private EditText etName;
    private Button btnShowTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        listView = (ListView) findViewById(R.id.lv_feeds);


        etName = (EditText) findViewById(R.id.etname);
        btnShowTweet = (Button) findViewById(R.id.showTweets);


        btnShowTweet.setOnClickListener(this);

        getTimeline(null);

    }


    public void getTimeline(String name) {


        if (name == null) {
            userTimeline = new UserTimeline.Builder()
                    .screenName("realDonaldTrump")
                    .build();
            userTimeline.next(null, callback);

            //adding the call back
        /*userTimeline.next(null, callback);*/
        } else {
            userTimeline = new UserTimeline.Builder()
                    .screenName(name)
                    .build();

            userTimeline.next(null, callback);
        }

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        // setListAdapter(adapter);

        listView.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();


        if (name != null) {
            //Calling function
            getTimeline(name);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.m_logout:
                logout();
                //  Toast.makeText(TestActivity.this,"Menue is logout",Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void logout() {

        Intent intent = new Intent(TestActivity.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        //for doing nothing when user press button
    }



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
