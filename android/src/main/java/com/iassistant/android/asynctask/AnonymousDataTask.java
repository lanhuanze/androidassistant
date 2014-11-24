package com.iassistant.android.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import com.iassistant.android.collector.AnonymousDataCollector;
import com.iassistant.android.entities.AbstractModule;
import com.iassistant.android.entities.AnonymousData;
import com.iassistant.android.http.AnonymousDataResponseHandler;
import com.iassistant.android.http.JsonExchanger;
import com.iassistant.android.http.Result;
import com.iassistant.android.util.GsonHelper;

import java.util.Arrays;

/**
 * Created by lan on 11/24/14.
 */
public class AnonymousDataTask extends AsyncTask<Object, Integer, Integer> {
    private Context mContext;
    public AnonymousDataTask(Context context) {
        mContext = context;
    }
    @Override
    protected Integer doInBackground(Object ... params) {
        AbstractModule data = new AnonymousDataCollector(mContext).collect();
        Result<AnonymousData> result = JsonExchanger.getInstance().put("/device/v1/anonymousdata/create", Arrays.asList(data), new AnonymousDataResponseHandler());

        System.out.println("Anonymous exchange result count:" + result.getCount() +", json:"  + GsonHelper.toJson(result.getObjects()));
        return 0;
    }
}
