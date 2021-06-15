package ru.realityfamily.takso_mobile.Logic;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import ru.realityfamily.takso_mobile.R;

public class Notify extends Application {

    NotificationManagerCompat notificationManager;
    NotificationChannel mChannel;
    String id = "my_channel_01";

    public void sendNotify(Context context, String Title, String ContentText) {
        if (notificationManager == null) {
            notificationManager = NotificationManagerCompat.from(context);
        }
        if (mChannel == null) {
            String name = "channel for Ring notify";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(id, name, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(Title)
                .setContentText(ContentText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setChannelId(id);

        notificationManager.notify(0, builder.build());
    }
}
