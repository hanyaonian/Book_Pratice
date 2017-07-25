package com.example.dell.brocastreceiver_pratice;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetStateReceiver netStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态注册广播
        //intentFilter = new IntentFilter();
        //网络变化时，系统发出的广播
        //intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //netStateReceiver = new NetStateReceiver();
        //registerReceiver(netStateReceiver, intentFilter);

        //静态注册广播并发送
        Button hello_butt = (Button)findViewById(R.id.hello_butt);
        hello_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netStateReceiver);
    }
}
