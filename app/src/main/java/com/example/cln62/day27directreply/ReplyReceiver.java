package com.example.cln62.day27directreply;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class ReplyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle input = RemoteInput.getResultsFromIntent(intent);
        if (input != null) {
            String myId = input.getString(MainActivity.KEY);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setContentTitle("request received from " + myId);
            builder.setContentText("This is notification content");
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(123, builder.build());
        }

    }
}
