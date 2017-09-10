package com.app.notificationpal;


import java.util.ArrayList;

public class ArbitraryConstraint implements Constraint {
    public ArrayList<RegisteredNotification> subscribers = new ArrayList<>();

    @Override
    public Void notifySubscribers() {
        for (RegisteredNotification registeredNotification : subscribers) {
            registeredNotification.constraintSatisfied(this);
        }
        return null;
    }

    @Override
    public void addSubscriber(RegisteredNotification registeredNotification) {
        subscribers.add(registeredNotification);
    }
}
