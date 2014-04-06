package com.inkglobal.techtest.clock.view.configuration;

import static com.inkglobal.techtest.ArgumentHelper.rejectIfNull;
import static com.inkglobal.techtest.ArgumentHelper.rejectIfZeroOrLess;

import com.inkglobal.techtest.BerlinClock;
import com.inkglobal.techtest.LampState;

/**
 * Contains information for configuring a {@link BerlinClock}.
 *
 * @author Kieran Rafferty
 */
public class ClockConfiguration
{
    private final LampState primaryLampState;
    private final LampState alternativeLampState;
    private final int rowOneLength;
    private final int rowTwoLength;
    private final int rowThreeLength;
    private final int rowFourLength;
    private final int rowThreeAltLampOffset;

    private ClockConfiguration(Builder builder)
    {
        this.primaryLampState = builder.primaryLampState;
        this.alternativeLampState = builder.alternativeLampState;
        this.rowOneLength = builder.rowOneLength;
        this.rowTwoLength = builder.rowTwoLength;
        this.rowThreeLength = builder.rowThreeLength;
        this.rowFourLength = builder.rowFourLength;
        this.rowThreeAltLampOffset = builder.rowThreeAltLampOffset;
    }

    /**
     * @return The {@link LampState} used to determine the primary color that the lamp will be drawn
     *         with when the lamp is lit.
     */
    public LampState getPrimaryActiveLampState()
    {
        return primaryLampState;
    }

    /**
     * @return The {@link LampState} used to determine the alternative color that the lamp will be
     *         drawn with when the lamp is lit.
     */
    public LampState getAlternativeActiveLampState()
    {
        return alternativeLampState;
    }

    /**
     * @return the number of lamps that should be visible on the first row of the clock.
     */
    public int getRowOneLength()
    {
        return rowOneLength;
    }

    /**
     * @return the number of lamps that should be visible on the second row of the clock.
     */
    public int getRowTwoLength()
    {
        return rowTwoLength;
    }

    /**
     * @return the number of lamps that should be visible on the third row of the clock.
     */
    public int getRowThreeLength()
    {
        return rowThreeLength;
    }

    /**
     * @return the number of lamps that should be visible on the fourth row of the clock.
     */
    public int getRowFourLength()
    {
        return rowFourLength;
    }

    /**
     * @return Indicates the spacing separating every lamp that should be drawn in a different color
     *         on row three.
     */
    public int getRowThreeAltLampStateOffset()
    {
        return rowThreeAltLampOffset;
    }

    /**
     * Builder class used for constructing {@link ClockConfiguration} objects.
     *
     * @author Kieran Rafferty
     */
    public static class Builder
    {
        private LampState primaryLampState = LampState.RED;
        private LampState alternativeLampState = LampState.YELLOW;
        private int rowOneLength = 4;
        private int rowTwoLength = 4;
        private int rowThreeLength = 11;
        private int rowFourLength = 4;
        private int rowThreeAltLampOffset = 3;

        /**
         * @param lampState
         *            The {@link LampState} used to determine the primary color that the lamp will
         *            be drawn with when the lamp is lit. {@link LampState#RED} by default. Can not
         *            be {@code null}.
         * @return The builder to be used for constructing the {@code RelatedAppointment}.
         * @throws IllegalArgumentException
         *             If the parameter is {@code null} or empty.
         */
        public Builder setPrimaryLampState(LampState lampState)
        {
            primaryLampState = rejectIfOff(rejectIfNull(lampState, "lampState"));
            return this;
        }

        /**
         * @param lampState
         *            The {@link LampState} used to determine the alternative color that the lamp
         *            will be drawn with when the lamp is lit. {@link LampState#YELLOW} by default.
         *            an not be {@code null}.
         * @return The builder to be used for constructing the {@code RelatedAppointment}.
         * @throws IllegalArgumentException
         *             If the parameter is {@code null} or empty.
         */
        public Builder setAlternativeLampState(LampState lampState)
        {
            alternativeLampState = rejectIfOff(rejectIfNull(lampState, "lampState"));
            return this;
        }

        /**
         * @param length
         *            The number of lamps that should be visible on the first row of the clock.
         * @return The builder to be used for constructing the {@code RelatedAppointment}.
         * @throws IllegalArgumentException
         *             If the parameter is less than or equal to zero.
         */
        public Builder setRowOneLength(int length)
        {
            rowOneLength = rejectIfZeroOrLess(length, "length");
            return this;
        }

        /**
         * @param length
         *            The number of lamps that should be visible on the second row of the clock.
         * @return The builder to be used for constructing the {@code RelatedAppointment}.
         * @throws IllegalArgumentException
         *             If the parameter is less than or equal to zero.
         */
        public Builder setRowTwoLength(int length)
        {
            rowTwoLength = rejectIfZeroOrLess(length, "length");
            return this;
        }

        /**
         * @param length
         *            The number of lamps that should be visible on the third row of the clock.
         * @return The builder to be used for constructing the {@code RelatedAppointment}.
         * @throws IllegalArgumentException
         *             If the parameter is less than or equal to zero.
         */
        public Builder setRowThreeLength(int length)
        {
            rowThreeLength = rejectIfZeroOrLess(length, "length");
            return this;
        }

        /**
         * @param length
         *            The number of lamps that should be visible on the fourth row of the clock.
         * @return The builder to be used for constructing the {@code RelatedAppointment}.
         * @throws IllegalArgumentException
         *             If the parameter is less than or equal to zero.
         */
        public Builder setRowFourLength(int length)
        {
            rowFourLength = rejectIfZeroOrLess(length, "length");
            return this;
        }

        /**
         * @param rowThreeAltLampOffset
         *            Indicates the spacing separating every lamp that should be drawn in a
         *            different color on row three.
         * @return The builder to be used for constructing the {@code RelatedAppointment}.
         * @throws IllegalArgumentException
         *             If the parameter is less than or equal to zero.
         */
        public Builder setRowThreeAltLampStateOffset(int rowThreeAltLampOffset)
        {
            this.rowThreeAltLampOffset = rejectIfZeroOrLess(rowThreeAltLampOffset, "rowThreeAltLampOffset");
            return this;
        }

        /**
         * @return the {@code ClockConfiguration} which was built using all of the previously set
         *         values on this builder.
         */
        public ClockConfiguration build()
        {
            return new ClockConfiguration(this);
        }

        /**
         * @param lampState
         *            The {@link LampState} to check.
         * @return if the parameter passed is equal to {@link LampState#OFF}.
         */
        private static LampState rejectIfOff(LampState lampState)
        {
            if (LampState.OFF.equals(lampState))
            {
                throw new IllegalArgumentException("lampState cannot be 'LampState.OFF'");
            }
            return lampState;
        }
    }
}
