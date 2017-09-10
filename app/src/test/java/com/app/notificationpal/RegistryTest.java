package com.app.notificationpal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegistryTest {
    private Notifier mockNotifier;
    private Registry registry;
    private Constraint arbitraryConstraint;

    @Before
    public void beforeEach() {
        mockNotifier = Mockito.mock(Notifier.class);
        registry = new Registry(mockNotifier);
        arbitraryConstraint = new ArbitraryConstraint();
    }

    @Test
    public void canRegisterNotification() {
        assertEquals(0, registry.count());
        registry.registerNotification("Hello!", arbitraryConstraint);
        assertEquals(1, registry.count());
        arbitraryConstraint.notifySubscribers();
        verify(mockNotifier).createNotification("Hello!");
        assertEquals(1, registry.count());
    }

    @Test
    public void canUnRegisterNotification() {
        registry.registerNotification("Hello!", arbitraryConstraint);

        arbitraryConstraint.notifySubscribers();
        verify(mockNotifier, times(1)).createNotification("Hello!");


        assertEquals(1, registry.count());
        registry.unregisterNotification("Hello!");
        assertEquals(0, registry.count());

        arbitraryConstraint.notifySubscribers();

        verify(mockNotifier, times(1)).createNotification("Hello!");
    }
}
