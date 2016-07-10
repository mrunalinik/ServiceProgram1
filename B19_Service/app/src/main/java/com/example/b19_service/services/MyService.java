package com.example.b19_service.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chethan on 11/30/2015.
 */
public class MyService extends Service{

    private final String TAG = "MyService";
    private IBinder myBinder = new MyBinder();


    public class MyBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Oncreate is called");
    }


    public void downloadData(){
        Log.i(TAG,"download method is called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG,"onDestroy is called");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
