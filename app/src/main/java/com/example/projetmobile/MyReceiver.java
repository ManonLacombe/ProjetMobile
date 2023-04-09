package com.example.projetmobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * this class allows to manage the receiver
 */
public class MyReceiver extends BroadcastReceiver {

    /**
     * this method manages the change of state of the airplane mode
     * @param context is the concerned activity
     * @param intent allows to recover the extra of the intent
     */
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "Modification de l'état du mode avion", Toast.LENGTH_LONG).show();
    }
}
