package com.example.dell.brocastreceiver_pratice;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetStateReceiver netStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        //网络变化时，系统发出的广播
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netStateReceiver = new NetStateReceiver();
        registerReceiver(netStateReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netStateReceiver);
    }
}
