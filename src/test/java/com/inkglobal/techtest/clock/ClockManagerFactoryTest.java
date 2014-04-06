package com.inkglobal.techtest.clock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.inkglobal.techtest.clock.internal.DefaultClockManager;

/**
 * Tests for the {@link ClockManagerFactory}.
 *
 * @author Kieran Rafferty
 */
public class ClockManagerFactoryTest
{
    @Test
    public void testGetClockManager()
    {
        ClockManager expectedClockManager = new DefaultClockManager();
        ClockManager actualClockManager = ClockManagerFactory.getClockManager();
        assertThat(actualClockManager.getClass().getName(), equalTo(expectedClockManager.getClass()
                .getName()));
    }
}
