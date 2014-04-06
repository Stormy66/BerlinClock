package com.inkglobal.techtest.clock.view;

import com.inkglobal.techtest.LampState;
import com.inkglobal.techtest.clock.view.configuration.ClockConfiguration;

/**
 * Interface for a Berlin Clock.
 *
 * @author Kieran Rafferty
 */
public interface BerlinClockView
{
    /**
     * Creates the first hour row.
     *
     * @param size
     *            The number of hours the row represents.
     */
    public void createHourRowOne(int size);

    /**
     * Creates the second hour row.
     *
     * @param size
     *            The number of hours the row represents.
     */
    public void createHourRowTwo(int size);

    /**
     * Creates the first minute row.
     *
     * @param size
     *            The number of minutes the row represents.
     */
    public void createMinuteRowOne(int size);

    /**
     * Creates the second minute row.
     *
     * @param size
     *            The number of minutes the row represents.
     */
    public void createMinuteRowTwo(int size);

    /**
     * Sets whether the lamp counting the seconds is lit.
     *
     * @param lit
     *            {@code true} if the seconds lamp is lit, {@code false} if the lamp should be off.
     */
    public void setSecondLampLit(boolean lit);

    /**
     * Sets the specified index of the first hour row to on.
     *
     * @param index
     *            The index of the lamp within the row to turn on. Should be 1 indexed. If the value
     *            passed is 0 then the row will be set to 'off'.
     * @throws IllegalArgumentException
     *             if the index is negative or exceeds the size of the row.
     */
    public void setHourRowOneIndexLit(int index);

    /**
     * Sets the specified index of the second hour row to on.
     *
     * @param index
     *            The index of the lamp within the row to turn on. Should be 1 indexed. If the value
     *            passed is 0 then the row will be set to 'off'.
     * @throws IllegalArgumentException
     *             if the index is negative or exceeds the size of the row.
     */
    public void setHourRowTwoIndexLit(int index);

    /**
     * Sets the specified index of the first minute row to on.
     *
     * @param index
     *            The index of the lamp within the row to turn on. Should be 1 indexed. If the value
     *            passed is 0 then the row will be set to 'off'.
     * @param useAltColor
     *            {@code true} if the index should be drawn in the alternative color, {@code false}
     *            otherwise.
     * @throws IllegalArgumentException
     *             if the index is negative or exceeds the size of the row.
     */
    public void setMinuteRowOneIndexLit(int index, boolean useAltColor);

    /**
     * Sets the specified index of the second minute row to on.
     *
     * @param index
     *            The index of the lamp within the row to turn on. Should be 1 indexed. If the value
     *            passed is 0 then the row will be set to 'off'.
     * @throws IllegalArgumentException
     *             if the index is negative or exceeds the size of the row.
     */
    public void setMinuteRowTwoIndexLit(int index);

    /**
     * Turns the entire first hour row to off.
     */
    public void setHourRowOneOff();

    /**
     * Turns the entire second hour row to off.
     */
    public void setHourRowTwoOff();

    /**
     * Turns the entire first minute row to off.
     */
    public void setMinuteRowOneOff();

    /**
     * Turns the entire second minute row to off.
     */
    public void setMinuteRowTwoOff();

    /**
     * Redraws the component according to its current state.
     */
    public void redraw();

    /**
     * Resets the component as if the time were 00:00:00.
     */
    public void reset();
}
