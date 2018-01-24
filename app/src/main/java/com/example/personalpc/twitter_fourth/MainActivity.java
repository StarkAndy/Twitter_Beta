package com.example.personalpc.twitter_fourth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {

    //creating button for twiterLoginButton
    TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Twitter.initialize(this);

        setContentView(R.layout.activity_main);

        initializeLoginButton();
    }

    //function will be called when the user success
    public void successInResult(){
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        String secret = authToken.secret;

        //calling the login function which will help to login
        login(session);
    }

    public void initializeLoginButton(){
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                successInResult();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this,"Authentication failed",Toast.LENGTH_LONG).show();
            }
        });


    }

    public void login(TwitterSession session){

        //Getting username
        String username=session.getUserName();

        //going to the second activity
        Intent intent=new Intent(MainActivity.this,TestActivity.class);
        intent.putExtra("username",username);

        startActivity(intent);
    }

    //for sending back

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
      //for doing nothing  when user press button
    }



}


