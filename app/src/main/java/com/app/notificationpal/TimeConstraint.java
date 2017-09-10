package com.app.notificationpal;


import java.util.Arrays;

public class TimeConstraint extends SubscribedConstraint {
    private final Range range;
    private final Granularity granularity;
    private final int n;

    public enum Range {
        AFTER, BEFORE, UPON;

        public boolean isSatisfied(int n, int m) {
            switch (this) {
                case AFTER:
                    return m >= n;
                case BEFORE:
                    return m < n;
                case UPON:
                    return m == n;
            }
            return false;
        }
    }

    public enum Granularity {
        YEAR, MONTH, DAY, HOUR, MINUTE, SECOND;

        public int relevantUnit(int year, int month, int day, int hour, int minute, int second) {
            int indexOfRelevantUnit = Arrays.asList(values()).indexOf(this);
            return new int[]{year, month, day, hour, minute, second}[indexOfRelevantUnit];
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
