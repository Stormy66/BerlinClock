package com.inkglobal.techtest.clock;

/**
 * An abstract implementation of the {@link ClockController} which implements the wrapping of the
 * various set time methods.
 *
 * @author Kieran Rafferty
 */
public abstract class AbstractClockController implements ClockController
{
    /**
     * Stores the hour value currently set on the controller.
     */
    protected int hour;
    /**
     * Stores the minute value currently set on the controller.
     */
    protected int minute;
    /**
     * Stores the second value currently set on the controller.
     */
    protected int second;

    @Override
    public void setHour(int hour)
    {
        this.hour = (minute == 0 && second == 0) ? normalizeTime(Math.abs(hour), 25)
                : normalizeTime(Math.abs(hour), 24);
    }

    @Override
    public void setMinute(int minute)
    {
        this.minute = normalizeTime(Math.abs(minute), 60);
        if (minute > 0 && hour == 24)
        {
            this.hour = 0;
        }
    }

    @Override
    public void setSecond(int second)
    {
        this.second = normalizeTime(Math.abs(second), 60);
        if (second > 0 && hour == 24)
        {
            this.hour = 0;
        }
    }

    private int normalizeTime(int time, int maxTime)
    {
        return time - (time / maxTime) * maxTime;
    }
}
