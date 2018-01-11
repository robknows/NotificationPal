package com.app.notificationpal;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class Notifier {
    public static final String notificationChannel = "notificationpal";

    private NotificationManager notificationManager;
    private Context appContext;

    public Notifier(NotificationManager notificationManager, Context appContext) {
        this.notificationManager = notificationManager;
        this.appContext = appContext;
    }

    public void createNotification(String title) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(notificationChannel, notificationChannel, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        // Note: setSmallIcon, setContentTitle and setContentText are all mandatory fields that must be set.
        Notification notification = new NotificationCompat.Builder(appContext, notificationChannel)
                .setSmallIcon(R.drawable.notificationpal_icon)
                .setContentTitle(title)
                .setContentText("")
                .build();

        notificationManager.notify(title.hashCode(), notification);
    }
}
