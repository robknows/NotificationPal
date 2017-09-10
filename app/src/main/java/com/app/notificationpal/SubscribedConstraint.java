package com.app.notificationpal;


import java.util.ArrayList;

public abstract class SubscribedConstraint {
    public ArrayList<RegisteredNotification> subscribers = new ArrayList<>();

    public void addSubscriber(RegisteredNotification registeredNotification) {
        subscribers.add(registeredNotification);
    }
}
