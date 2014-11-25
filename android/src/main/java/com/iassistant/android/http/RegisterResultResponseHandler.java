package com.iassistant.android.http;

import com.google.gson.reflect.TypeToken;
import com.iassistant.android.entities.RegisterResult;

import java.lang.reflect.Type;

/**
 * Created by lan on 11/25/14.
 */
public class RegisterResultResponseHandler implements ResponseHandler {
    @Override
    public Type getType() {
        Type type = new TypeToken<Result<RegisterResult>>() {
        }.getType();
        return type;
    }
}
