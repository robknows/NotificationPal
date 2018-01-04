package com.app.notificationpal;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.NOTIFICATION_SERVICE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NotifierTest {
    private Context appContext = InstrumentationRegistry.getTargetContext();
    private NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(NOTIFICATION_SERVICE);

    @Test
    public void usesAppContext() throws Exception {
        assertEquals("com.app.notificationpal", appContext.getPackageName());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Test
    public void canCreateNotification() {
        new Notifier(notificationManager, appContext).createNotification("West Dulwich to Victoria train in 15 minutes");
        assertEquals("com.app.notificationpal", notificationManager.getActiveNotifications()[0].getPackageName());
    }
}
