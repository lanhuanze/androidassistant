package com.iassistant.android.http;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.iassistant.android.prefs.Config;
import com.iassistant.android.util.GsonHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lan on 11/24/14.
 */
public class JsonExchanger {

    private static final Logger log = LoggerFactory.getLogger(JsonExchanger.class);
    private HttpClient mHttpClient = null;

    private JsonExchanger() {
        mHttpClient = new JsonHttpClient();
    }

    public static JsonExchanger getInstance() {
        return Holder._INST;
    }

    private static final class Holder {
        public static final JsonExchanger _INST = new JsonExchanger();
    }

    public <Req, Res> Result<Res> put(String url, List<Req> objs, ResponseHandler handler) {
        String json = GsonHelper.toJson(objs);
        Result<Res> r = Result.newResult();
        String baseUrl = Config.getInstance().getServerBaseUrl();
        HttpPut put = new HttpPut( baseUrl + url);
        HttpEntity entity = null;
        try {
            entity = new StringEntity(json);
            put.setEntity(entity);
            put.addHeader("content-type", "application/json;charset=UTF8");

        HttpResponse res = execute(put);
        if(res != null) {
            int code = res.getStatusLine().getStatusCode();
            if(code >= 200 && code < 300) {
                String respJson = EntityUtils.toString(res.getEntity(), "UTF8");
                if( ! TextUtils.isEmpty(respJson)) {

                    log.info("Get from server:" + respJson) ;
                    Result<Res> result = GsonHelper.jsonToResult(respJson, handler.getType());
                    return result;
                }
            }
        }
        } catch (UnsupportedEncodingException e) {
            log.error("put get error:" + e.getMessage());
        }catch (IOException e) {
            log.error("put get error:" + e.getMessage());
        }
        return r;
    }

    private HttpResponse execute(HttpRequestBase req) {
        HttpResponse response = null;
        try {
            response = mHttpClient.execute(req);
        }catch (IOException e) {
            log.error("execute get error:" + e.getMessage());
        }
        return response;
    }
}
