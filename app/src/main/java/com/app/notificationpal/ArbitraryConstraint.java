package com.app.notificationpal;


public class ArbitraryConstraint extends SubscribedConstraint implements Constraint {
    @Override
    public Void notifySubscribers() {
        for (RegisteredNotification registeredNotification : subscribers) {
            registeredNotification.constraintSatisfied(this);
        }
        return null;
    }
}
