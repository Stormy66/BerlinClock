package com.inkglobal.techtest.mocks;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.inkglobal.techtest.clock.ClockManager;
import com.inkglobal.techtest.clock.view.configuration.ClockConfiguration;

/**
 * A Mock implementation of the {@link ClockManager}.
 *
 * @author Kieran Rafferty
 */
public class MockClockManager implements ClockManager
{
    private int timesAddTimerActionCalled = 0;
    private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
    private int[] time = new int[] { 1, 2, 3 };

    @Override
    public ClockConfiguration getClockConfiguration()
    {
        return new ClockConfiguration.Builder().build();
    }

    /**
     * @param time Sets the time array to return.
     */
    public void setTime(int[] time)
    {
        this.time = time;
    }

    @Override
    public int[] getTime()
    {
        return time;
    }

    @Override
    public void addTimerAction(ActionListener listener)
    {
        actionListeners.add(listener);
        timesAddTimerActionCalled++;
    }

    @Override
    public void removeTimerAction(ActionListener listener)
    {
        actionListeners.remove(listener);
    }

    /**
     * @return the number of times the {@link #addTimerAction} has been called.
     */
    public int getTimesAddTimerActionCalled()
    {
        return timesAddTimerActionCalled;
    }

    /**
     * Notifies all the listeners to tick over.
     */
    public void notifyListeners()
    {
        for (ActionListener listener : actionListeners)
        {
            listener.actionPerformed(null);
        }
    }

}
