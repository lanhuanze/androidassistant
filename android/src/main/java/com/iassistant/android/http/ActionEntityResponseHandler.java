package com.iassistant.android.http;

import com.google.gson.reflect.TypeToken;
import com.iassistant.android.entities.ActionEntity;

import java.lang.reflect.Type;

/**
 * Created by lan on 11/25/14.
 */
public class ActionEntityResponseHandler implements ResponseHandler {
    @Override
    public Type getType() {
        Type type = new TypeToken<Result<ActionEntity>>() {
        }.getType();
        return type;
    }
}
