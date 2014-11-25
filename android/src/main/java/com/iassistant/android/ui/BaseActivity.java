package com.iassistant.android.ui;

import android.app.Activity;

/**
 * Created by lan on 11/25/14.
 */
public class BaseActivity extends Activity {
    protected <E> E byId(int id) {
        return (E)findViewById(id);
    }

    protected String get(int id) {
        return getResources().getString(id);
    }

    protected String get(int id, Object ... args) {
        return getResources().getString(id, args);
    }
}
