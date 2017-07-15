package com.app.notificationpal;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

public class NotificationMakerTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, true);

    private Context appContext = InstrumentationRegistry.getTargetContext();
    private NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(NOTIFICATION_SERVICE);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Test
    public void canMakeNotification() {
        onView(withId(R.id.submitNotification)).perform(ViewActions.click());
        assertEquals("Local Train Time", notificationManager.getActiveNotifications()[0].getNotification().extras.getString(Notification.EXTRA_TITLE));
    }
}
