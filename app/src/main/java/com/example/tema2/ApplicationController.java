package com.example.tema2;

import android.app.Application;

import androidx.room.Room;

public class ApplicationController extends Application {
    private static ApplicationController mInstance;

    private static myAppDatabase mAppDatabase;

    public static ApplicationController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                myAppDatabase.class, "Andra.db").build();
    }
    public static myAppDatabase getAppDatabase(){
        return mAppDatabase;
    }
}
