package com.inkglobal.techtest;

import static com.inkglobal.techtest.ArgumentHelper.rejectIfNull;
import static com.inkglobal.techtest.ArgumentHelper.rejectIfNegative;
import static com.inkglobal.techtest.ArgumentHelper.rejectIfZeroOrLess;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.inkglobal.techtest.clock.controller.BerlinClockController;
import com.inkglobal.techtest.util.ExceptionTester;

/**
 * Tests for the {@link ArgumentHelper}.
 *
 * @author Kieran Rafferty
 */
public class ArgumentHelperTest
{
    private static final String CANNOT_BE_NULL_MESSAGE = "%s cannot be null.";
    private static final String CANNOT_BE_NEGATIVE_MESSAGE = "%s cannot be negative.";
    private static final String CANNOT_BE_ZERO_OR_NEGATIVE_MESSAGE = "%s must be greater than zero.";

    @Test
    public void testRejectIfNull()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                String data = null;
                rejectIfNull(data, "data");
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_NULL_MESSAGE, "data"));

        String data = "valid";
        String result = rejectIfNull(data, "data");

        assertTrue(data == result);
    }

    @Test
    public void testRejectIfNegative()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                int data = -1;
                rejectIfNegative(data, "data");
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_NEGATIVE_MESSAGE, "data"));

        int data = 0;
        int result = rejectIfNegative(data, "data");

        assertTrue(data == result);

        data = 1;
        result = rejectIfNegative(data, "data");

        assertTrue(data == result);
    }

    @Test
    public void testRejectIfZeroOrNegative()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                int data = -1;
                rejectIfZeroOrLess(data, "data");
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_NEGATIVE_MESSAGE, "data"));

        exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                int data = 0;
                rejectIfZeroOrLess(data, "data");
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_NEGATIVE_MESSAGE, "data"));

        int data = 1;
        int result = rejectIfNegative(data, "data");

        assertTrue(data == result);
    }
}
