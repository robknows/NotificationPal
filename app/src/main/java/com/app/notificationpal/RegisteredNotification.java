package com.app.notificationpal;

import java.util.HashMap;

class RegisteredNotification {
    private boolean active = false;
    private HashMap<Constraint, Boolean> constraints = new HashMap<>();
    private Runnable task;
    public String title;

    public RegisteredNotification(final Notifier notifier, final String notificationTitle) {
        title = notificationTitle;
        task = () -> notifier.createNotification(notificationTitle);
    }

    public void subscribe(Constraint constraint) {
        constraints.put(constraint, false);
        constraint.addSubscriber(this);
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public void constraintSatisfied(Constraint constraint) {
        if (constraints.containsKey(constraint)) {
            constraints.put(constraint, true);
        }
        if (constraints.values().stream().allMatch(Boolean::valueOf)) {
            trigger();
        }
    }

    private void trigger() {
        if (task != null && active) {
            task.run();
        }
    }
}
