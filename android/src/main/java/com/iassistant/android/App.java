package com.iassistant.android;

import android.app.Application;
import com.iassistant.android.prefs.Config;

/**
 * Created by lan on 11/24/14.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Config.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
