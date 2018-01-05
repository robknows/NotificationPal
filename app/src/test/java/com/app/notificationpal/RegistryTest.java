package com.app.notificationpal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegistryTest {
    private Notifier mockNotifier;
    private Registry registry;
    private Constraint arbitraryConstraint;
    private TimeConstraint timeConstraint;
    private HashSet<Constraint> arbitraryConstraintOnly;
    private HashSet<Constraint> bothConstraints;

    @Before
    public void beforeEach() {
        mockNotifier = Mockito.mock(Notifier.class);
        registry = new Registry(mockNotifier);

        arbitraryConstraint = new ArbitraryConstraint();
        timeConstraint = new TimeConstraint(TimeConstraint.Range.AFTER, TimeConstraint.Granularity.HOUR, 16);

        arbitraryConstraintOnly = new HashSet<>();
        arbitraryConstraintOnly.add(arbitraryConstraint);

        bothConstraints = new HashSet<>();
        bothConstraints.add(arbitraryConstraint);
        bothConstraints.add(timeConstraint);
    }

    @Test
    public void canRegisterNotification() {
        assertEquals(0, registry.count());
        registry.registerNotification("Hello!", arbitraryConstraintOnly);
        assertEquals(1, registry.count());
        arbitraryConstraint.notifySubscribers();
        verify(mockNotifier).createNotification("Hello!");
        assertEquals(1, registry.count());
    }

    @Test
    public void canUnRegisterNotification() {
        registry.registerNotification("Hello!", arbitraryConstraintOnly);

        arbitraryConstraint.notifySubscribers();
        verify(mockNotifier, times(1)).createNotification("Hello!");


        assertEquals(1, registry.count());
        registry.unregisterNotification("Hello!");
        assertEquals(0, registry.count());

        arbitraryConstraint.notifySubscribers();

        verify(mockNotifier, times(1)).createNotification("Hello!");
    }

    @Test
    public void notificationWithTwoConstraintsDoesntTriggerAfterOnlyOneNotifies() {
        registry.registerNotification("Hello!", bothConstraints);

        arbitraryConstraint.notifySubscribers();

        verify(mockNotifier, never()).createNotification("Hello!");
    }

    @Test
    public void notificationWithTwoConstraintsTriggersAfterBothNotify() {
        registry.registerNotification("Hello!", bothConstraints);

        arbitraryConstraint.notifySubscribers();
        timeConstraint.notifySubscribers();

        verify(mockNotifier).createNotification("Hello!");
    }
}
