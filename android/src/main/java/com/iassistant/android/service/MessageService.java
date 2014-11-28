package com.iassistant.android.service;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.iassistant.android.asynctask.CallbackAsyncTask;
import com.iassistant.android.asynctask.GetAsyncTask;
import com.iassistant.android.entities.ActionEntity;
import com.iassistant.android.http.ActionEntityResponseHandler;
import com.iassistant.android.http.Result;
import com.iassistant.android.util.GsonHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lan on 11/25/14.
 */
@Slf4j
public class MessageService extends IntentService {

    private VNCServerManagerService vncServer;
    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        String actionId = (String) intent.getStringExtra("actionId");
        if(Actions.ACTION_POLL.equals(action)) {
            GetAsyncTask<ActionEntity, Object> task = new GetAsyncTask<ActionEntity, Object>("/action/v1/retrieve/" + actionId, callback, null, new ActionEntityResponseHandler());
            task.execute();
        }
    }

    public static interface Actions {
        String ACTION_POLL = "poll";
    }

    public MessageService() {
        super("MessageService");
    }

    private CallbackAsyncTask.Callback<ActionEntity> callback = new CallbackAsyncTask.Callback<ActionEntity>() {
        @Override
        public void onResult(Result<ActionEntity> result) {
            log.info("result:" + GsonHelper.toJson(result));
            if(result.getCount() > 0) {
                if(vncServer != null) {
                    vncServer.startServer();
                }
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder binder) {
            vncServer = ((VNCServerManagerService.MyBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            vncServer = null;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bindService(new Intent(this, VNCServerManagerService.class), mConnection, Context.BIND_AUTO_CREATE);
    }
}
