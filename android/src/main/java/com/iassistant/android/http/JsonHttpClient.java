package com.iassistant.android.http;

import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

/**
 * Created by lan on 11/24/14.
 */
public class JsonHttpClient extends AndroidHttpClient {

    private static final int DEFAULT_TIMEOUT = 70000; // ms

    public JsonHttpClient()
    {
        super(getTimeoutParams(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT));
        setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        setReuseStrategy(new NoConnectionReuseStrategy());
    }

    public JsonHttpClient(int connectionTimeout, int soTimeout)
    {
        super(getTimeoutParams(connectionTimeout, soTimeout));
        setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        setReuseStrategy(new NoConnectionReuseStrategy());
    }

    private static BasicHttpParams getTimeoutParams(int connectionTimeout, int soTimeout)
    {
        BasicHttpParams httpParams = new BasicHttpParams();
        httpParams.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, connectionTimeout);
        httpParams.setParameter(HttpConnectionParams.SO_TIMEOUT, soTimeout);
        return httpParams;
    }
}
