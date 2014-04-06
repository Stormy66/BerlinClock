package com.inkglobal.techtest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Tests for the {@link LampState} enum.
 *
 * @author Kieran Rafferty
 */
public class LampStateTest
{
    @Test
    public void testLampState()
    {
        for (LampState state : LampState.values())
        {
            switch (state)
            {
                case OFF:
                    assertThat(state.getSimpleDisplay(), equalTo("O"));
                    break;
                case RED:
                    assertThat(state.getSimpleDisplay(), equalTo("R"));
                    break;
                case YELLOW:
                    assertThat(state.getSimpleDisplay(), equalTo("Y"));
                    break;
                case BLUE:
                    assertThat(state.getSimpleDisplay(), equalTo("B"));
                    break;
                default:
                    throw new RuntimeException(String.format("%s was not expected", state.name()));
            }
        }
    }
}
