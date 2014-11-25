package com.iassistant.android.asynctask;

import com.iassistant.android.http.JsonExchanger;
import com.iassistant.android.http.ResponseHandler;
import com.iassistant.android.http.Result;

import java.util.List;

/**
 * Created by lan on 11/25/14.
 */
public class PutAsyncTask<E, R> extends CallbackAsyncTask<E,R> {

    private List<R> objects;

    public PutAsyncTask(String url, Callback callback, List<R> objs, ResponseHandler handler) {
        super(url, callback, handler);
        objects = objs;
    }

    @Override
    protected Result<E> doInBackground(Object... voids) {
        Result<E> result = JsonExchanger.getInstance().put(url, objects, handler);
        return result;
    };
}
