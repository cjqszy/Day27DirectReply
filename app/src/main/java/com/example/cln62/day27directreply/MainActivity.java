package com.example.cln62.day27directreply;

import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String KEY = "key";
    Button button, buttonCancel;
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReplyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 321, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                RemoteInput remoteInput = new RemoteInput.Builder(KEY)
                        .setLabel("Reply here").build();

                NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, "id",
                        pendingIntent).addRemoteInput(remoteInput).build();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setContentTitle("Title");
                builder.setContentText("This is notification content");
                builder.addAction(action);
                manager.notify(123, builder.build());
            }
        });

        buttonCancel = findViewById(R.id.button2);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(123);
            }
        });
    }
}
