package com.iassistant.android.util;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.iassistant.android.http.Result;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lan on 11/21/14.
 */
public class GsonHelper {
    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    public static <T> T fromJson(String json, Class<T> type) {
        return GSON.fromJson(json, type);
    }

    public static <T> List<T> fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static <T> Result<T> jsonToResult(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }

    public static boolean getAsBoolean(JsonObject obj, String key) {
        return "true".equalsIgnoreCase(obj.get(key).getAsString());
    }

    public static List<String> getAsStringArray(JsonObject obj, String key) {
        JsonArray array = obj.get(key).getAsJsonArray();
        List<String> strs = Lists.newArrayList();
        for (int i = 0, size = array.size(); i < size; i++) {
            strs.add(array.get(i).getAsString());
        }
        return strs;
    }
}
