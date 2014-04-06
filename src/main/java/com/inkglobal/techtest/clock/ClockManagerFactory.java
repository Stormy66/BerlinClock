package com.inkglobal.techtest.clock;

import com.inkglobal.techtest.clock.internal.DefaultClockManager;

/**
 * Factory for producing the {@link ClockManager} to be used in the application.
 *
 * @author Kieran Rafferty
 */
public class ClockManagerFactory
{
    /**
     * @return The appropriate implementation of the {@link ClockManager}.
     */
    public static ClockManager getClockManager()
    {
        // implement service locator logic here if necessary
        // otherwise return the default
        return new DefaultClockManager();
    }
}
