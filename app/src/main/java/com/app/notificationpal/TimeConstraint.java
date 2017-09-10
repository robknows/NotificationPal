package com.app.notificationpal;


public class TimeConstraint extends SubscribedConstraint implements Constraint {
    private final Range range;
    private final Granularity granularity;
    private final int n;

    public enum Range {
        AFTER;

        public boolean isSatisfied(int n, int m) {
            return m > n;
        }
    }

    public enum Granularity {
        HOUR;

        public int relevantUnit(int year, int month, int day, int hour, int minute, int second) {
            return hour;
        }
    }

    public TimeConstraint(Range range, Granularity granularity, int n) {
        this.range = range;
        this.granularity = granularity;
        this.n = n;
    }

    public void timeUpdate(int year, int month, int day, int hour, int minute, int second) {
        int m = granularity.relevantUnit(year, month, day, hour, minute, second);

        if (range.isSatisfied(n, m)) {
            notifySubscribers();
        }
    }
}
