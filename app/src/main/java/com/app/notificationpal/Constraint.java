package com.app.notificationpal;


interface Constraint {
    Void notifySubscribers();

    void addSubscriber(RegisteredNotification registeredNotification);
}
