package com.iassistant.android.prefs;

import android.content.Context;
import com.iassistant.android.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by lan on 11/24/14.
 */
public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    private static final String SERVER_BASE_URL = "SERVER_BASE_URL";

    private static final class Holder {
        public static final Config _INST = new Config();
    }

    private Config() {
        configs = new Properties();
    }

    public boolean init(Context c) {
        if(!initialized) {
            try {
                configs.loadFromXML(c.getResources().openRawResource(R.raw.config));
            } catch (IOException e) {
                log.error("Error loading R.raw.config");
                return false;
            }
        }
        initialized = true;
        return true;
    }

    public static synchronized final Config getInstance() {
        return Holder._INST;
    }

    public String getServerBaseUrl() {
       return configs.getProperty(SERVER_BASE_URL);
    }

    private Properties configs = null;
    private boolean initialized = false;
}
