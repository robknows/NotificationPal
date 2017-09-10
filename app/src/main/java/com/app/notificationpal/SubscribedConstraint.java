package com.app.notificationpal;


import java.util.ArrayList;

public abstract class SubscribedConstraint implements Constraint {
    public ArrayList<RegisteredNotification> subscribers = new ArrayList<>();

    @Override
    public void addSubscriber(RegisteredNotification registeredNotification) {
        subscribers.add(registeredNotification);
    }

    @Override
    public Void notifySubscribers() {
        for (RegisteredNotification registeredNotification : subscribers) {
            registeredNotification.constraintSatisfied(this);
        }
        return null;
    }
}
