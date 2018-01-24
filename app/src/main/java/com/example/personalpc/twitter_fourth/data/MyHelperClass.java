package com.example.personalpc.twitter_fourth.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.personalpc.twitter_fourth.datautils.TwitterContract;

/**
 * Created by Personal Pc on 1/23/2018.
 */

public class MyHelperClass {

   //String to create table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TwitterContract.TwitterEntry.TABLE_NAME + " (" +
                    TwitterContract.TwitterEntry._ID + " INTEGER PRIMARY KEY," +
                    TwitterContract.TwitterEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TwitterContract.TwitterEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    //String to delete entries data from table
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TwitterContract.TwitterEntry.TABLE_NAME;




    //Creating instance for MyHelperClass
    private TwitterTableCreater twitterTableCreater;
    //Instance for sqlite helper class
    private SQLiteDatabase sqLiteDatabase;

    //Constructor
    public MyHelperClass(Context context){

        twitterTableCreater=new TwitterTableCreater(context);


    }
    //function will assign the sqlite database object

    public void open(){

        sqLiteDatabase=twitterTableCreater.getWritableDatabase();

    }

    //function will
    public void close(){

        sqLiteDatabase.close();

        //making sqlite data base object to null
        if(sqLiteDatabase!=null){
            sqLiteDatabase=null;
        }

    }





    class TwitterTableCreater extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public TwitterTableCreater(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}
