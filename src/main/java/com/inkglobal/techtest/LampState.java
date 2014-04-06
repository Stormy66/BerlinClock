package com.inkglobal.techtest;

/**
 * Enumeration for deciding the state of the lamp.
 *
 * @author Kieran Rafferty
 */
public enum LampState
{
    RED("R"), YELLOW("Y"), BLUE("B"),  OFF("O");

    private String simpleDisplay;


    private LampState(String simpleDisplay)
    {
        this.simpleDisplay = simpleDisplay;
    }

    /**
     * @return The simple display value for the given {@code LampState}.
     */
    public String getSimpleDisplay()
    {
        return simpleDisplay;
    }
}
