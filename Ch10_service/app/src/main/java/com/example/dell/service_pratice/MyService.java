package com.example.dell.service_pratice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private DownLoadBinder binder = new DownLoadBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("service:", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //就算服务已启动，只要启动就会调用此函数，而onCreate仅一次。
        Log.i("service:", "onStartCommand, thread:" + Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("service:", "onDestroy");
    }
    class DownLoadBinder extends Binder {
        public void startDownload() {
            Log.i("downloadBinder:", "startDownload");
        }
        public void getProgress() {
            Log.i("downloadBinder:", "progress");
        }
    }
}
