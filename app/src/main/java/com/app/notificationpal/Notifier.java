package com.app.notificationpal;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;

public class Notifier {
    private NotificationManager notificationManager;
    private Context appContext;

    public Notifier(NotificationManager notificationManager, Context appContext) {
        this.notificationManager = notificationManager;
        this.appContext = appContext;
    }

    public void createNotification(String title) {
        // Note: setSmallIcon, setContentTitle and setContentText are all mandatory fields that must be set.
        Notification notification = new Builder(appContext)
                .setSmallIcon(R.drawable.notificationpal_icon)
                .setContentTitle(title)
                .setContentText("")
                .build();

//        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
