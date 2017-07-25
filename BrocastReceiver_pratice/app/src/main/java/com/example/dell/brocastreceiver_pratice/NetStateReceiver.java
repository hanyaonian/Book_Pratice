package com.example.dell.brocastreceiver_pratice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by DELL on 2017/7/25.
 */

public class NetStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo.isAvailable() == true && networkInfo != null) {
            Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
        } else if (networkInfo.isAvailable() == false && networkInfo != null) {
            Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }
}
