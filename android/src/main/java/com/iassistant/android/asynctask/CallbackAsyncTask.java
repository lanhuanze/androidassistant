package com.iassistant.android.asynctask;

import android.os.AsyncTask;
import com.iassistant.android.entities.AnonymousData;
import com.iassistant.android.http.AnonymousDataResponseHandler;
import com.iassistant.android.http.JsonExchanger;
import com.iassistant.android.http.ResponseHandler;
import com.iassistant.android.http.Result;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lan on 11/25/14.
 */
public abstract  class CallbackAsyncTask<E,R> extends AsyncTask<Object, Integer, Result<E>> {

    protected Callback callback;
    protected ResponseHandler handler;
    protected String url;

    public CallbackAsyncTask(String url, Callback callback, ResponseHandler handler) {
        this.callback = callback;
        this.handler = handler;
        this.url = url;
    }

    @Override
    protected void onPostExecute(Result<E> result) {
        //super.onPostExecute(result);
        callback.onResult(result);
    }


    public static interface Callback<E> {
        void onResult(Result<E> result);
    }
}
