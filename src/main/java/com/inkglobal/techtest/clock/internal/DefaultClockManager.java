package com.inkglobal.techtest.clock.internal;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Timer;

import com.inkglobal.techtest.clock.ClockManager;
import com.inkglobal.techtest.clock.view.configuration.ClockConfiguration;

/**
 * A default implementation of the {@link ClockManager}. Starts a timer that updates once per
 * second.
 *
 * @author Kieran Rafferty
 */
public class DefaultClockManager implements ClockManager
{
    private Timer timer = new Timer(1000, null);

    /**
     * Constructor.
     */
    public DefaultClockManager()
    {
        timer.start();
    }

    @Override
    public ClockConfiguration getClockConfiguration()
    {
        return new ClockConfiguration.Builder().build();
    }

    @Override
    public int[] getTime()
    {
        int[] time = new int[3];
        Calendar cal = Calendar.getInstance();
        time[0] = cal.get(Calendar.HOUR_OF_DAY);
        time[1] = cal.get(Calendar.MINUTE);
        time[2] = cal.get(Calendar.SECOND);
        return time;
    }

    @Override
    public void addTimerAction(ActionListener listener)
    {
        timer.addActionListener(listener);
    }

    @Override
    public void removeTimerAction(ActionListener listener)
    {
        timer.removeActionListener(listener);
    }

}
