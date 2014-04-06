package com.inkglobal.techtest.clock;

import java.awt.event.ActionListener;
import com.inkglobal.techtest.clock.view.configuration.ClockConfiguration;

/**
 * Interface for Clock Managers.
 *
 * @author Kieran Rafferty
 */
public interface ClockManager
{
    /**
     * @return A {@link ClockConfiguration} for configuring a {@link ClockController}.
     */
    public ClockConfiguration getClockConfiguration();

    /**
     * @return The time in a simple array format. The array is of size 3 where the 0 index is the
     *         hour, the 1 index the minute, and the 2 index the second.
     */
    public int[] getTime();

    /**
     * Adds a listener to the clock managers timer.
     *
     * @param listener
     *            The {@code ActionListener} which will receive the event from the timer. A call to
     *            the {@link ActionListener#actionPerformed(java.awt.event.ActionEvent)} is made
     *            every second.
     */
    public void addTimerAction(ActionListener listener);

    /**
     * Removes a listener to the clock managers timer.
     *
     * @param listener
     *            The {@code ActionListener} to remove.
     */
    public void removeTimerAction(ActionListener listener);
}
