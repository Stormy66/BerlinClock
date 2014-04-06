package com.inkglobal.techtest.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Abstract testing class for ensuring that exceptions thrown are those expected. Circumvents the
 * issue where the JUnit annotation could catch another exception of the same type being thrown.
 *
 * @author Kieran Rafferty
 */
public abstract class ExceptionTester
{
    /**
     * This method executes the{@link #check()} method and asserts that the exception thrown is of
     * the expected type with the expected message.
     *
     * @param exceptionType
     *            The type of exception expected to be thrown.
     * @param message
     *            The expected message.
     */
    public <T extends Exception> void testExceptionThrown(Class<T> exceptionType, String message)
    {
        try
        {
            check();
        }
        catch (Exception e)
        {
            if (exceptionType.isInstance(e))
            {
                assertThat(e.getMessage(), equalTo(message));
                return;
            }
            throw new RuntimeException(String.format(
                    "Expected exception of type %s, but got %s instead.", exceptionType, e
                            .getClass().getName()), e);
        }
        throw new RuntimeException("No Exception was thrown");
    }

    /**
     * This method should be implemented and call the method in question. No try catch should
     * surround the line expected to throw an error.
     */
    public abstract void check();
}
