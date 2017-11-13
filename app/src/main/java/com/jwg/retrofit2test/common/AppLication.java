package com.jwg.retrofit2test.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class AppLication extends Application {
    private static Context sContext;
    private static AppLication sInstance;
    public static int SCREEN_WIDTH;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    private void init() {
        sContext = getApplicationContext();
        sInstance = this;
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point screen = new Point();
        SCREEN_WIDTH = Math.min(screen.x, screen.y);
    }

    public static Context getContext() {
        if (sContext == null) {
            throw new NullPointerException("APP Context is Null");
        }
        return sContext;
    }

    public static AppLication getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("APP sInstance is Null");
        }
        return sInstance;
    }


}
