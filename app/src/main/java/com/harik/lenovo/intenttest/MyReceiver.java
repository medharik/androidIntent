package com.harik.lenovo.intenttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String nomprenom = intent.getStringExtra("nomprenom");
        Toast.makeText(context,"nom & prénom : "+nomprenom,Toast.LENGTH_LONG).show();
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");

    }
}
