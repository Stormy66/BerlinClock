package com.inkglobal.techtest.clock;

/**
 * Interface for Clock Controllers.
 *
 * @author Kieran Rafferty
 */
public interface ClockController
{
    /**
     *
     * Sets the hour ensuring it is valid first.
     *
     * @param hour
     *            If the value passed is greater than 23, then 24 will be deducted until the value
     *            is a number between 0 and 23. If it is less than zero, then the value will be
     *            taken as the absolute value. If the value passed is 24 (or can be reduced to 24)
     *            and both the minute and the second are equal to 0 then the hour will be set to 24.
     */
    void setHour(int hour);

    /**
     * Sets the minute ensuring it is valid first.
     *
     * @param minute
     *            The minute to set. If the value passed is greater than 59, then 60 will be
     *            deducted until the value is a number between 0 and 59. If it is less than zero,
     *            then the value will be taken as the absolute value. If the hour value is 24 and
     *            the value set here is not 0, then the hour will be automatically set to 0.
     */
    void setMinute(int minute);

    /**
     * Sets the second ensuring it is valid first.
     *
     * @param second
     *            The second to set. If the value passed is greater than 59, then 60 will be
     *            deducted until the value is a number between 0 and 59. If it is less than zero,
     *            then the value will be taken as the absolute value. If the hour value is 24 and
     *            the value set here is not 0, then the hour will be automatically set to 0.
     */
    void setSecond(int second);

    /**
     * Redraws the view that this object is managing with the current time set on this object.
     */
    void redraw();
}
