package com.iassistant.android.http;

import com.google.gson.reflect.TypeToken;
import com.iassistant.android.entities.AnonymousData;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lan on 11/24/14.
 */
public class AnonymousDataResponseHandler implements ResponseHandler {
    @Override
    public Type getType() {
        Type type = new TypeToken<Result<AnonymousData>>() {
        }.getType();
        return type;
    }
}
