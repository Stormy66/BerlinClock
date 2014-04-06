package com.inkglobal.techtest.clock.controller;

import static com.inkglobal.techtest.ArgumentHelper.rejectIfNull;
import static com.inkglobal.techtest.ArgumentHelper.rejectIfNegative;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.inkglobal.techtest.clock.AbstractClockController;
import com.inkglobal.techtest.clock.ClockManager;
import com.inkglobal.techtest.clock.view.BerlinClockView;
import com.inkglobal.techtest.clock.view.configuration.ClockConfiguration;

/**
 * An implementation of the ClockController for use with {@linkBerlinClockView}.
 *
 * @author Kieran Rafferty
 */
public class BerlinClockController extends AbstractClockController
{
    private final int secondRowTickValue;
    private final int fourthRowTickValue;
    private final int rowThreeAltLampOffset;

    private final BerlinClockView view;
    private final ClockManager manager;

    /**
     * Constructor.
     *
     * @param view
     *            The {@link BerlinClockView} which this object is controlling.
     * @param manager
     *            The {@link ClockManager} for configuring the controller.
     * @throws IllegalArgumentException
     *             if either the {@code BerlinClockView} or the {@code ClockManager} is {@code null}
     *             .
     */
    public BerlinClockController(BerlinClockView view, ClockManager manager)
    {
        this.view = rejectIfNull(view, "view");
        this.manager = rejectIfNull(manager, "manager");

        ClockConfiguration clockConfiguration = this.manager.getClockConfiguration();
        this.view.createHourRowOne(clockConfiguration.getRowOneLength());
        this.view.createHourRowTwo(clockConfiguration.getRowTwoLength());
        this.view.createMinuteRowOne(clockConfiguration.getRowThreeLength());
        this.view.createMinuteRowTwo(clockConfiguration.getRowFourLength());

        secondRowTickValue = clockConfiguration.getRowTwoLength() + 1;
        fourthRowTickValue = clockConfiguration.getRowFourLength() + 1;

        rowThreeAltLampOffset = clockConfiguration.getRowThreeAltLampStateOffset();

        int[] time = manager.getTime();
        hour = time[0];
        minute = time[1];
        second = time[2];
        initialiseView();

        manager.addTimerAction(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                incrementTime();
                redraw();
            }
        });
    }

    @Override
    public void setHour(int hour)
    {
        super.setHour(hour);
        initialiseView();
    }

    @Override
    public void setMinute(int minute)
    {
        super.setMinute(minute);
        initialiseView();
    }

    @Override
    public void setSecond(int second)
    {
        super.setSecond(second);
        initialiseView();
    }

    @Override
    public void redraw()
    {
        view.setSecondLampLit(isLampOn());

        int[] hourRows = calculateHour(hour);
        view.setHourRowOneIndexLit(hourRows[0]);
        view.setHourRowTwoIndexLit(hourRows[1]);

        int[] minuteRows = calculateMinute(minute);
        view.setMinuteRowOneIndexLit(minuteRows[0], (minuteRows[0]) % rowThreeAltLampOffset == 0);
        view.setMinuteRowTwoIndexLit(minuteRows[1]);

        view.redraw();
    }

    private void incrementTime()
    {
        second++;
        if (second > 59)
        {
            second = 0;
            minute++;
            if (minute > 59)
            {
                minute = 0;
                hour++;
                if ((minute != 0 || second != 0) && hour > 23)
                {
                    hour = 0;
                }
            }
        }
        else if (second == 1 && hour == 24)
        {
            hour = 0;
        }
    }

    /**
     * Calculates the distribution of hours across the two rows.
     *
     * @param hour
     *            The current hour to distribute across the two rows.
     * @return An {@code int} array of size 2 where the first value is the number of lamps that
     *         should be lit on the first row and the second value those that should be lit on the
     *         second row.
     */
    protected int[] calculateHour(int hour)
    {
        return calculateRowDistrubution(secondRowTickValue, hour);
    }

    /**
     * Calculates the distribution of minutes across the two rows.
     *
     * @param minute
     *            The current minute to distribute across the two rows.
     * @return An {@code int} array of size 2 where the first value is the number of lamps that
     *         should be lit on the first row and the second value those that should be lit on the
     *         second row.
     */
    protected int[] calculateMinute(int minute)
    {
        return calculateRowDistrubution(fourthRowTickValue, minute);
    }

    private int[] calculateRowDistrubution(int rowTickValue, int value)
    {
        int[] result = new int[2];

        result[0] = rejectIfNegative(value, "value") / rowTickValue;
        result[1] = value % rowTickValue == rowTickValue ? 0 : value % rowTickValue;
        return result;
    }

    private void initialiseView()
    {
        int[] hourRows = calculateHour(hour);
        int[] minuteRows = calculateMinute(minute);

        for (int i = 1; i <= hourRows[0]; i++)
        {
            view.setHourRowOneIndexLit(i);
        }

        for (int i = 1; i <= hourRows[1]; i++)
        {
            view.setHourRowTwoIndexLit(i);
        }

        for (int i = 1; i <= minuteRows[0]; i++)
        {
            view.setMinuteRowOneIndexLit(i, (i % rowThreeAltLampOffset == 0));
        }

        for (int i = 1; i <= minuteRows[1]; i++)
        {
            view.setMinuteRowTwoIndexLit(i);
        }

        view.setSecondLampLit(isLampOn());
    }

    private boolean isLampOn()
    {
        return second / 2 % 2 == 0;
    }
}
