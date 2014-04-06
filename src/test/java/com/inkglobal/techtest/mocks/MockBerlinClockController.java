package com.inkglobal.techtest.mocks;

import com.inkglobal.techtest.clock.ClockManager;
import com.inkglobal.techtest.clock.controller.BerlinClockController;
import com.inkglobal.techtest.clock.view.BerlinClockView;

/**
 * <p>
 * A mock extension of the {@link BerlinClockController} allowing inspection of properties.
 * </p>
 * <p>
 * Copyright &copy 2014 Cerner Corporation
 * </p>
 *
 * @author Kieran Rafferty
 */
public class MockBerlinClockController extends BerlinClockController
{

    /**
     * Constructor.
     *
     * @param view
     *            A {@link BerlinClockView}. Cannot be {@code null}.
     * @param manager
     *            A {@link ClockManager}. Cannot be {@code null}.
     */
    public MockBerlinClockController(BerlinClockView view, ClockManager manager)
    {
        super(view, manager);
    }

    /**
     * @return the hour currently set on the controller.
     */
    public int getHour()
    {
        return hour;
    }

    /**
     * @return the minute currently set on the controller.
     */
    public int getMinute()
    {
        return minute;
    }

    /**
     * @return the second currently set on the controller.
     */
    public int getSecond()
    {
        return second;
    }
}
