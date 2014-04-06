package com.inkglobal.techtest.clock;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Abstract tests for implementations of the {@link AbstractClockController}.
 *
 * @author Kieran Rafferty
 */
public abstract class TestAbstractClockController
{
    private AbstractClockController controller;

    @Test
    public void testAbstractSetHour()
    {
        controller = createController();
        controller.setMinute(0);
        controller.setSecond(0);

        controller.setHour(24);
        assertThat(controller.hour, equalTo(24));

        controller.setHour(-24);
        assertThat(controller.hour, equalTo(24));

        controller.setSecond(1);
        controller.setHour(24);
        assertThat(controller.hour, equalTo(0));

        controller.setSecond(0);
        controller.setMinute(1);
        controller.setHour(24);
        assertThat(controller.hour, equalTo(0));

        controller.setHour(71);
        assertThat(controller.hour, equalTo(23));

        controller.setHour(16);
        assertThat(controller.hour, equalTo(16));
    }

    @Test
    public void testAbstractSetMinute()
    {
        controller = createController();

        controller.setMinute(59);
        assertThat(controller.minute, equalTo(59));

        controller.setMinute(60);
        assertThat(controller.minute, equalTo(0));

        controller.setMinute(-60);
        assertThat(controller.minute, equalTo(0));
    }

    @Test
    public void testAbstractSetMinuteCausesHourToTickOver()
    {
        controller = createController();
        controller.setMinute(0);
        controller.setSecond(0);

        controller.setHour(24);
        assertThat(controller.hour, equalTo(24));

        controller.setMinute(0);
        assertThat(controller.minute, equalTo(0));
        assertThat(controller.hour, equalTo(24));

        controller.setMinute(1);
        assertThat(controller.minute, equalTo(1));
        assertThat(controller.hour, equalTo(0));
    }

    @Test
    public void testAbstractSetSecond()
    {
        controller = createController();
        controller.setSecond(59);
        assertThat(controller.second, equalTo(59));

        controller.setSecond(60);
        assertThat(controller.second, equalTo(0));

        controller.setSecond(-60);
        assertThat(controller.second, equalTo(0));
    }

    @Test
    public void testAbstractSetSecondCausesHourToTickOver()
    {
        controller = createController();
        controller.setMinute(0);
        controller.setSecond(0);

        controller.setHour(24);
        assertThat(controller.hour, equalTo(24));

        controller.setSecond(0);
        assertThat(controller.second, equalTo(0));
        assertThat(controller.hour, equalTo(24));

        controller.setSecond(1);
        assertThat(controller.second, equalTo(1));
        assertThat(controller.hour, equalTo(0));
    }

    /**
     * Creates the controller for the abstract class to use.
     */
    public abstract AbstractClockController createController();
}
