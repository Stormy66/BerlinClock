package com.inkglobal.techtest.clock.internal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.inkglobal.techtest.clock.view.configuration.ClockConfiguration;

/**
 * Tests for the {@link DefaultClockManager}.
 *
 * @author Kieran Rafferty
 */
public class DefaultClockManagerTest
{
    private final int[] counter = new int[1];

    private DefaultClockManager clockManager = new DefaultClockManager();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTime()
    {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        int currentSecond = Calendar.getInstance().get(Calendar.SECOND);

        int[] retrievedTime = clockManager.getTime();

        assertThat(retrievedTime.length, equalTo(3));
        assertThat(retrievedTime[0], equalTo(currentHour));
        assertThat(retrievedTime[1], equalTo(currentMinute));
        assertThat(retrievedTime[2], equalTo(currentSecond));
    }

    @Test
    public void testGetClockConfiguration()
    {
        ClockConfiguration expected = new ClockConfiguration.Builder().build();
        ClockConfiguration actual = clockManager.getClockConfiguration();

        assertThat(actual.getPrimaryActiveLampState(), equalTo(expected.getPrimaryActiveLampState()));
        assertThat(actual.getAlternativeActiveLampState(), equalTo(expected.getAlternativeActiveLampState()));
        assertThat(actual.getRowOneLength(), equalTo(expected.getRowOneLength()));
        assertThat(actual.getRowTwoLength(), equalTo(expected.getRowTwoLength()));
        assertThat(actual.getRowThreeLength(), equalTo(expected.getRowThreeLength()));
        assertThat(actual.getRowFourLength(), equalTo(expected.getRowFourLength()));
        assertThat(actual.getRowThreeAltLampStateOffset(), equalTo(expected.getRowThreeAltLampStateOffset()));
    }
}
