package com.teluscare.android.utility;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import java.lang.ref.WeakReference;

/**
 * Created by SandeepY on 18112019
 **/


public class TeluscareApp extends MultiDexApplication {

    private static WeakReference<TeluscareApp> wApp = new WeakReference<>(null);

    public static TeluscareApp getInstance() {
        return wApp.get();
    }

    public static Context getContext() {
        TeluscareApp app = wApp.get();
        return app != null ? app.getApplicationContext() : new TeluscareApp().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        wApp.clear();
        wApp = new WeakReference<>(this);
        //FirebaseApp.initializeApp(this);
        TeluscareAPI.setAPIEnvironment();
    }
}
