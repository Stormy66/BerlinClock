package com.inkglobal.techtest;

/**
 * Simple assistant for checking arguments.
 *
 * @author Kieran Rafferty
 */
public class ArgumentHelper
{
    private static final String CANNOT_BE_NULL_MESSAGE = "%s cannot be null.";
    private static final String CANNOT_BE_NEGATIVE_MESSAGE = "%s cannot be negative.";
    private static final String CANNOT_BE_ZERO_OR_NEGATIVE_MESSAGE = "%s must be greater than zero.";

    /**
     * Throws an {@code IllegalArgumentException} if the object passed is {@code null}.
     *
     * @param object
     *            The object to test for {@code null}.
     * @param argument
     *            The name of argument that this method is checking.
     * @return The object that was checked for {@code null} if said object was not {@code null}.
     */
    public static <T> T rejectIfNull(T object, String argument)
    {
        if (object != null)
        {
            return object;
        }
        throw new IllegalArgumentException(String.format(CANNOT_BE_NULL_MESSAGE, argument));
    }

    /**
     * Throws an {@code IllegalArgumentException} if the value passed is negative.
     *
     * @param i
     *            The value to test whether it is negative.
     * @param argument
     *            The name of argument that this method is checking.
     * @return The value to be checked provided it passes the check.
     */
    public static int rejectIfNegative(int i, String argument)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException(String.format(CANNOT_BE_NEGATIVE_MESSAGE, argument));
        }
        return i;
    }

    /**
     * Throws an {@code IllegalArgumentException} if the value passed is zero or less.
     *
     * @param i
     *            The value to test whether it is zero or less.
     * @param argument
     *            The name of argument that this method is checking.
     * @return The value to be checked provided it passes the check.
     */
    public static int rejectIfZeroOrLess(int i, String argument)
    {
        if (i <= 0)
        {
            throw new IllegalArgumentException(String.format(CANNOT_BE_ZERO_OR_NEGATIVE_MESSAGE,
                    argument));
        }
        return i;
    }
}
