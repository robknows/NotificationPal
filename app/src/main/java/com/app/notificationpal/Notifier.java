package com.app.notificationpal;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Notifier {
    private Context appContext;

    public Notifier(Context appContext) {
        this.appContext = appContext;
    }

    public void createNotification(String title, String message) {
        // Note: setSmallIcon, setContentTitle and setContentText are all mandatory fields that must be set.
        Notification notification = new Builder(appContext)
                .setSmallIcon(R.drawable.notificationpal_icon)
                .setContentTitle(title)
                .setContentText(message)
                .build();

        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
