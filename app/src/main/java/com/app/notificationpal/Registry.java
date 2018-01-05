package com.app.notificationpal;


import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

public class Registry {
    private ArrayList<RegisteredNotification> registeredNotifications;
    private Notifier notifier;

    public Registry(Notifier notifier) {
        this.notifier = notifier;
        registeredNotifications = new ArrayList<>();
    }

    public void registerNotification(String notificationTitle, Set<Constraint> constraints) {
        RegisteredNotification newNotification = new RegisteredNotification(notifier, notificationTitle);
        constraints.forEach(newNotification::subscribe);
        newNotification.activate();
        registeredNotifications.add(newNotification);
    }

    public void unregisterNotification(String notificationTitle) {
        Predicate<RegisteredNotification> notificationToRemove = registeredNotification -> notificationTitle.equals(registeredNotification.title);

        registeredNotifications.stream()
                .filter(notificationToRemove)
                .forEach(RegisteredNotification::deactivate);

        registeredNotifications.removeIf(notificationToRemove);
    }

    public int count() {
        return registeredNotifications.size();
    }

    public boolean contains(String notificationTitle) {
        return registeredNotifications.stream().anyMatch(new Predicate<RegisteredNotification>() {
            @Override
            public boolean test(RegisteredNotification registeredNotification) {
                return notificationTitle.equals(registeredNotification.title);
            }
        });
    }
}
