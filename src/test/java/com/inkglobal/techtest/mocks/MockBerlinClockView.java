package com.inkglobal.techtest.mocks;

import com.inkglobal.techtest.clock.view.BerlinClockView;

public class MockBerlinClockView implements BerlinClockView
{
    private int currentRowOneIndex = 0;
    private int currentRowTwoIndex = 0;
    private int currentRowThreeIndex = 0;
    private int currentRowFourIndex = 0;
    private boolean rowThreeIsUsingAltColor = false;

    private boolean secondsLampLit = true;

    @Override
    public void createHourRowOne(int size)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void createHourRowTwo(int size)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void createMinuteRowOne(int size)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void createMinuteRowTwo(int size)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSecondLampLit(boolean lit)
    {
        secondsLampLit = lit;
    }

    @Override
    public void setHourRowOneIndexLit(int index)
    {
        currentRowOneIndex = index;
    }

    @Override
    public void setHourRowTwoIndexLit(int index)
    {
        currentRowTwoIndex = index;
    }

    @Override
    public void setMinuteRowOneIndexLit(int index, boolean useAltColor)
    {
        rowThreeIsUsingAltColor = useAltColor;
        currentRowThreeIndex = index;
    }

    @Override
    public void setMinuteRowTwoIndexLit(int index)
    {
        currentRowFourIndex = index;
    }

    @Override
    public void setHourRowOneOff()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHourRowTwoOff()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMinuteRowOneOff()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMinuteRowTwoOff()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void redraw()
    {
        // TODO Auto-generated method stub

    }

    public int getRowOneIndex()
    {
        return currentRowOneIndex;
    }

    public int getRowTwoIndex()
    {
        return currentRowTwoIndex;
    }

    public int getRowThreeIndex()
    {
        return currentRowThreeIndex;
    }

    public int getRowFourIndex()
    {
        return currentRowFourIndex;
    }

    public boolean isSecondLampLit()
    {
        return secondsLampLit;
    }

    public boolean isRowThreeUsingAltColor()
    {
        return rowThreeIsUsingAltColor;
    }

    @Override
    public void reset()
    {
        // TODO Auto-generated method stub

    }
}
