package com.example.b19_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.b19_service.services.MyService;

public class MainActivity extends AppCompatActivity {

    private Button startServiceButton, stopServiceButton, downloadButton;
    private Intent intent;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = (Button) findViewById(R.id.startServiceBtn);
        stopServiceButton = (Button) findViewById(R.id.stopServiceBtn);
        downloadButton = (Button) findViewById(R.id.downloadBtn);

        startServiceButton.setOnClickListener(clickListener);
        stopServiceButton.setOnClickListener(clickListener);
        downloadButton.setOnClickListener(clickListener);

        intent = new Intent(MainActivity.this, MyService.class);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()){

                case R.id.startServiceBtn:
                    //Start the service
                    startService(intent);
                    break;

                case R.id.stopServiceBtn:
                    //Stop the service
                    stopService(intent);
                    break;

                case R.id.downloadBtn:
                    //Call the download method in the service class

                    if(myService != null)
                        myService.downloadData();
                    break;
            }

        }
    };


    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder myBinder) {
            MyService.MyBinder b = (MyService.MyBinder) myBinder;
            myService = b.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unbindService(serviceConnection);
    }
}
