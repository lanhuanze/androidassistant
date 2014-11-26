package com.iassistant.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import com.iassistant.android.R;
import com.iassistant.android.entities.ActionEntity;
import com.iassistant.android.prefs.Prefs;
import com.iassistant.android.service.MessageService;
import com.iassistant.android.util.GsonHelper;
import com.iassistant.android.util.PubnubConst;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lan on 11/25/14.
 */
@Slf4j
public class DashboardActivity extends BaseActivity {

    private EditText messageEditText;
    private Prefs prefs = null;

    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        messageEditText = byId(R.id.message_editText);

        Intent intent = getIntent();

        String json = intent.getStringExtra("json");
        messageEditText.setText(json);

        prefs = Prefs.getInstance();

        pubnub = new Pubnub(PubnubConst.PUBLISH_KEY, PubnubConst.SUBSCRIBE_KEY);

        try {
            pubnub.subscribe(prefs.getDeviceId(), subscribeCallback);
        }catch (PubnubException e) {
            log.error("pubnub.subscribe get exception:" + e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pubnub.unsubscribe(prefs.getDeviceId());
    }

    private Pubnub pubnub = null;
    private Callback subscribeCallback = new Callback() {
        @Override
        public void successCallback(String channel, final Object message) {
            //super.successCallback(channel, message);
            log.info("SUBSCRIBE : CONNECT on channel:" + channel
                    + " : " + message.getClass() + " : "
                    + message.toString());
            mHandler.post(new Runnable() {
                public void run() {
                    messageEditText.setText(message.toString());
                }
            });
            ActionEntity ae = GsonHelper.fromJson(message.toString(), ActionEntity.class);
            if(ae != null) {
                Intent intent = new Intent(ae.getAction());
                intent.putExtra("actionId", ae.getId());
                intent.setClass(DashboardActivity.this, MessageService.class);
                startService(intent);
            }else {
                log.error("error convert " + message.toString() +" to json.");
            }
        }

        @Override
        public void errorCallback(String channel, Object message) {
            super.errorCallback(channel, message);
        }

        @Override
        public void connectCallback(String channel, Object message) {
            super.connectCallback(channel, message);
        }

        @Override
        public void reconnectCallback(String channel, Object message) {
            super.reconnectCallback(channel, message);
        }

        @Override
        public void disconnectCallback(String channel, Object message) {
            super.disconnectCallback(channel, message);
        }
    };
}