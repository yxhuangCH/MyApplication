package com.yxhuang.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 *    在单独进程中运行的 Service
 */
public class MessengerService extends Service {
    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstents.MSG_FROM_CLIENT:
                    Log.i(TAG, "receive mag from Client: " + msg.getData().getString("msg"));
                    break;

                default:
                    super.handleMessage(msg);

            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());


    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
