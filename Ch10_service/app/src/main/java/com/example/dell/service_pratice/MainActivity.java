package com.example.dell.service_pratice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import layout.MyIntentService;

public class MainActivity extends AppCompatActivity {
    private Button start_butt, stop_butt, bind_butt, unbind_butt, intentService_butt;
    private MyService.DownLoadBinder binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.DownLoadBinder) service;
            binder.startDownload();
            binder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_butt = (Button) findViewById(R.id.start_butt);
        stop_butt = (Button) findViewById(R.id.stop_butt);
        bind_butt = (Button) findViewById(R.id.bind_butt);
        intentService_butt = (Button)findViewById(R.id.intentService_butt);
        unbind_butt = (Button) findViewById(R.id.unbind_butt);
        start_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
        stop_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });
        bind_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
        unbind_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
        intentService_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity:", "CurrentThread:" + Thread.currentThread());
                startIntentService();
            }
        });
    }
    public void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
    public void stopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
    public void startIntentService() {
        Intent intent = new Intent(MainActivity.this, MyIntentService.class);
        startService(intent);
    }
}
