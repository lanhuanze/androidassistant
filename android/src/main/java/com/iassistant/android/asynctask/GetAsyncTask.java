package com.iassistant.android.asynctask;

import com.iassistant.android.http.JsonExchanger;
import com.iassistant.android.http.ResponseHandler;
import com.iassistant.android.http.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 11/25/14.
 */
public class GetAsyncTask<E, R> extends CallbackAsyncTask<E,R> {

    private Map<String, String> params;

    public GetAsyncTask(String url, Callback callback, Map<String, String> params, ResponseHandler handler) {
        super(url, callback, handler);
        this.params = params;
    }

    @Override
    protected Result<E> doInBackground(Object... voids) {
        Result<E> result = JsonExchanger.getInstance().get(url, params, handler);
        return result;
    };
}
