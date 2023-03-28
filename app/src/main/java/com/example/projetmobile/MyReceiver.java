package com.example.projetmobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "Modification de l'état du mode avion", Toast.LENGTH_LONG).show();
    }
}
