package com.inkglobal.techtest.clock.view.configuration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.inkglobal.techtest.LampState;
import com.inkglobal.techtest.util.ExceptionTester;

/**
 * Tests for the {@link ClockConfiguration}.
 *
 * @author Kieran Rafferty
 */
@SuppressWarnings({"unused", "javadoc"})
public class ClockConfigurationTest
{
    private static final String CANNOT_BE_NULL_MESSAGE = "%s cannot be null.";
    private static final String CANNOT_BE_OFF_MESSAGE = "lampState cannot be 'LampState.OFF'";
    private static final String CANNOT_BE_ZERO_OR_LESS_MESSAGE = "%s must be greater than zero.";

    @Test
    public void testNullPrimaryLampState()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObjecty = new ClockConfiguration.Builder()
                        .setPrimaryLampState(null).build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_NULL_MESSAGE, "lampState"));
    }

    @Test
    public void testOffPrimaryLampState()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObjecty = new ClockConfiguration.Builder().setPrimaryLampState(
                        LampState.OFF).build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_OFF_MESSAGE, "lampState"));
    }

    @Test
    public void testNullAlternativeLampState()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
            ClockConfiguration builtObjecty = new ClockConfiguration.Builder()
                    .setAlternativeLampState(null).build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_NULL_MESSAGE, "lampState"));
    }

    @Test
    public void testZeroRowOneLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowOneLength(0)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));
    }

    @Test
    public void testNegativeRowOneLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowOneLength(-1)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));
    }

    @Test
    public void testZeroRowTwoLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowTwoLength(0)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));
    }

    @Test
    public void testNegativeRowTwoLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowTwoLength(-1)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));
    }

    @Test
    public void testZeroRowThreeLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowThreeLength(0)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));
    }

    @Test
    public void testNegativeRowThreeLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowThreeLength(-1)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));

    }

    @Test
    public void testZeroRowFourLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowFourLength(0)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));
    }

    @Test
    public void testNegativeRowFourLength()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowFourLength(-1)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "length"));
    }

    @Test
    public void testZeroRowThreeOffset()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowThreeAltLampStateOffset(0)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "rowThreeAltLampOffset"));
    }

    @Test
    public void testNegativeRowThreeOffset()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowThreeAltLampStateOffset(-1)
                        .build();
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_ZERO_OR_LESS_MESSAGE, "rowThreeAltLampOffset"));
    }

    @Test
    public void testDefaultValues()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().build();

        assertThat(builtObject.getPrimaryActiveLampState(), equalTo(LampState.RED));
        assertThat(builtObject.getAlternativeActiveLampState(), equalTo(LampState.YELLOW));
        assertThat(builtObject.getRowOneLength(), equalTo(4));
        assertThat(builtObject.getRowTwoLength(), equalTo(4));
        assertThat(builtObject.getRowThreeLength(), equalTo(11));
        assertThat(builtObject.getRowFourLength(), equalTo(4));
        assertThat(builtObject.getRowThreeAltLampStateOffset(), equalTo(3));
    }

    @Test
    public void testSetPrimaryLampState()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().setPrimaryLampState(
                LampState.BLUE).build();

        assertThat(builtObject.getPrimaryActiveLampState(), equalTo(LampState.BLUE));
        assertThat(builtObject.getAlternativeActiveLampState(), equalTo(LampState.YELLOW));
    }

    @Test
    public void testSetAlternativeLampState()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().setAlternativeLampState(
                LampState.BLUE).build();

        assertThat(builtObject.getPrimaryActiveLampState(), equalTo(LampState.RED));
        assertThat(builtObject.getAlternativeActiveLampState(), equalTo(LampState.BLUE));
    }

    @Test
    public void testSetRowOneLength()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowOneLength(99)
                .build();

        assertThat(builtObject.getRowOneLength(), equalTo(99));
        assertThat(builtObject.getRowTwoLength(), equalTo(4));
        assertThat(builtObject.getRowThreeLength(), equalTo(11));
        assertThat(builtObject.getRowFourLength(), equalTo(4));
        assertThat(builtObject.getRowThreeAltLampStateOffset(), equalTo(3));
    }

    @Test
    public void testSetRowTwoLength()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowTwoLength(99)
                .build();

        assertThat(builtObject.getRowOneLength(), equalTo(4));
        assertThat(builtObject.getRowTwoLength(), equalTo(99));
        assertThat(builtObject.getRowThreeLength(), equalTo(11));
        assertThat(builtObject.getRowFourLength(), equalTo(4));
        assertThat(builtObject.getRowThreeAltLampStateOffset(), equalTo(3));
    }

    @Test
    public void testSetRowThreeLength()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowThreeLength(99)
                .build();

        assertThat(builtObject.getRowOneLength(), equalTo(4));
        assertThat(builtObject.getRowTwoLength(), equalTo(4));
        assertThat(builtObject.getRowThreeLength(), equalTo(99));
        assertThat(builtObject.getRowFourLength(), equalTo(4));
        assertThat(builtObject.getRowThreeAltLampStateOffset(), equalTo(3));
    }

    @Test
    public void testSetRowFourLength()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowFourLength(99)
                .build();

        assertThat(builtObject.getRowOneLength(), equalTo(4));
        assertThat(builtObject.getRowTwoLength(), equalTo(4));
        assertThat(builtObject.getRowThreeLength(), equalTo(11));
        assertThat(builtObject.getRowFourLength(), equalTo(99));
        assertThat(builtObject.getRowThreeAltLampStateOffset(), equalTo(3));
    }
    
    @Test
    public void testSetRowThreeAltLampStateOffset()
    {
        ClockConfiguration builtObject = new ClockConfiguration.Builder().setRowThreeAltLampStateOffset(99)
                .build();

        assertThat(builtObject.getRowOneLength(), equalTo(4));
        assertThat(builtObject.getRowTwoLength(), equalTo(4));
        assertThat(builtObject.getRowThreeLength(), equalTo(11));
        assertThat(builtObject.getRowFourLength(), equalTo(4));
        assertThat(builtObject.getRowThreeAltLampStateOffset(), equalTo(99));
    }
}
