package com.iassistant.android.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lan on 11/24/14.
 */
public class Prefs {

    private static final String PREFS_NAME = "prefs.xml";

    private static final String ACCOUNT_ID = "account_id";
    private static final String DEVICE_ID = "device_id";
    private static final String LOGINED = "logined";

    private SharedPreferences pref = null;

    public static final Prefs getInstance() {
        if(_INST == null) {
            _INST = new Prefs();
        }
        return _INST;
    }

    public boolean init(Context c) {
        if(!initialized) {
            pref = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
        initialized = true;
        return initialized;
    }

    private static Prefs _INST;

    public boolean isLogined() {
        return pref.getBoolean(LOGINED, false);
    }

    public boolean setLogined(boolean login) {
        SharedPreferences.Editor e = pref.edit();
        e.putBoolean(LOGINED, login);
        return e.commit();
    }

    public String getAccountId() {
        return pref.getString(ACCOUNT_ID, "");
    }

    public String getDeviceId() {
        return pref.getString(DEVICE_ID, "");
    }

    public boolean setAccountId(String id) {
        SharedPreferences.Editor e = pref.edit();
        e.putString(ACCOUNT_ID, id);
        return e.commit();
    }

    public boolean setDeviceId(String id) {
        SharedPreferences.Editor e = pref.edit();
        e.putString(DEVICE_ID, id);
        return e.commit();
    }

    private boolean initialized = false;
}
