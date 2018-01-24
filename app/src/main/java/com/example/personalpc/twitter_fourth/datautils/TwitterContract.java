package com.example.personalpc.twitter_fourth.datautils;

import android.provider.BaseColumns;

/**
 * Created by Personal Pc on 1/23/2018.
 */

public class TwitterContract {

    private TwitterContract(){

    }
    /* Inner class that defines the table contents */
    public static class TwitterEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}


