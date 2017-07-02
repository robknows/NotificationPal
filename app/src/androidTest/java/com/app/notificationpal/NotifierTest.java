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

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NotifierTest {
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void usesAppContext() throws Exception {
        assertEquals("com.app.notificationpal", appContext.getPackageName());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Test
    public void canCreateNotification() {
        new Notifier(appContext).createNotification("Local train time", "The next train from West Dulwich to Victoria is in 15 minutes");

        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(NOTIFICATION_SERVICE);
        assertEquals("com.app.notificationpal", notificationManager.getActiveNotifications()[0].getPackageName());
    }
}
