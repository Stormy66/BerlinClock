package com.inkglobal.techtest.clock.view.text;

import static com.inkglobal.techtest.ArgumentHelper.rejectIfNegative;
import static com.inkglobal.techtest.ArgumentHelper.rejectIfNull;

import com.inkglobal.techtest.LampState;
import com.inkglobal.techtest.clock.view.BerlinClockView;

/**
 * Console based implementation of the {@link BerlinClockView}.
 *
 * @author Kieran Rafferty
 */
public class TextBerlinClockView implements BerlinClockView
{
    private static final String INDEX_EXCEEDS_ROW_SIZE = "The specified index exceeds the size of the row";
    private static final String ROW_NOT_INITIALISED = "The row has not been initialised";
    private static final String WHITESPACE = " ";

    private LampState[] hourRowOne;
    private LampState[] hourRowTwo;
    private LampState[] minuteRowOne;
    private LampState[] minuteRowTwo;

    private LampState defaultPrimaryState = LampState.RED;
    private LampState defaultAlternativeState = LampState.YELLOW;
    private LampState secondLampOnState = LampState.OFF;

    private final PrintManager printManager;

    /**
     * Constructor. Initializes the {@link BerlinClockView} with the standard Berlin Clock
     * dimensions.
     */
    public TextBerlinClockView(PrintManager printManager)
    {
        this.printManager = rejectIfNull(printManager, "printManager");
    }

    @Override
    public void createHourRowOne(int size)
    {
        hourRowOne = new LampState[size];
        setHourRowOneOff();
    }

    @Override
    public void createHourRowTwo(int size)
    {
        hourRowTwo = new LampState[size];
        setHourRowTwoOff();
    }

    @Override
    public void createMinuteRowOne(int size)
    {
        minuteRowOne = new LampState[size];
        setMinuteRowOneOff();
    }

    @Override
    public void createMinuteRowTwo(int size)
    {
        minuteRowTwo = new LampState[size];
        setMinuteRowTwoOff();
    }

    @Override
    public void setSecondLampLit(boolean lit)
    {
        secondLampOnState = lit ? LampState.YELLOW : LampState.OFF;
    }

    @Override
    public void setHourRowOneIndexLit(int index)
    {
        if (index == 0)
        {
            setHourRowOneOff();
        }
        else
        {
            setRowLit(hourRowOne, index - 1, false);
        }
    }

    @Override
    public void setHourRowTwoIndexLit(int index)
    {
        if (index == 0)
        {
            setHourRowTwoOff();
        }
        else
        {
            setRowLit(hourRowTwo, index - 1, false);
        }
    }

    @Override
    public void setMinuteRowOneIndexLit(int index, boolean useAltLampState)
    {
        if (index == 0)
        {
            setMinuteRowOneOff();
        }
        else
        {
            setRowLit(minuteRowOne, index - 1, !useAltLampState);
        }
    }

    @Override
    public void setMinuteRowTwoIndexLit(int index)
    {
        if (index == 0)
        {
            setMinuteRowTwoOff();
        }
        else
        {
            setRowLit(minuteRowTwo, index - 1, true);
        }
    }

    @Override
    public void redraw()
    {
        printManager.println("    " + secondLampOnState.getSimpleDisplay());
        printManager.println(drawRow(hourRowOne, 12));
        printManager.println(drawRow(hourRowTwo, 12));
        printManager.println(drawRow(minuteRowOne, 12));
        printManager.println(drawRow(minuteRowTwo, 12));
        printManager.println("");
    }

    @Override
    public void setHourRowOneOff()
    {
        setRowOff(hourRowOne);
    }

    @Override
    public void setHourRowTwoOff()
    {
        setRowOff(hourRowTwo);
    }

    @Override
    public void setMinuteRowOneOff()
    {
        setRowOff(minuteRowOne);
    }

    @Override
    public void setMinuteRowTwoOff()
    {
        setRowOff(minuteRowTwo);
    }

    private String drawRow(LampState[] row, int leftPadding)
    {
        if (row != null)
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < leftPadding; i++)
            {
                sb.append(WHITESPACE);
            }

            for (int i = 0; i < row.length; i++)
            {
                sb.append(row[i].getSimpleDisplay());
            }

            return sb.toString();
        }
        return "";
    }

    private void setRowOff(LampState[] row)
    {
        for (int i = 0; i < row.length; i++)
        {
            row[i] = LampState.OFF;
        }
    }

    private void setRowLit(LampState[] row, int index, boolean useAltLampState)
    {
        if (row == null)
        {
            throw new IllegalStateException(ROW_NOT_INITIALISED);
        }
        checkIndexIsInAcceptableBounds(index, row);
        row[index] = useAltLampState ? defaultAlternativeState : defaultPrimaryState;
    }

    private void checkIndexIsInAcceptableBounds(int index, LampState[] row)
    {
        if (rejectIfNegative(index, "index") >= row.length)
        {
            throw new IllegalArgumentException(INDEX_EXCEEDS_ROW_SIZE);
        }
    }

    @Override
    public void reset()
    {
        setHourRowOneOff();
        setHourRowTwoOff();
        setMinuteRowOneOff();
        setMinuteRowTwoOff();
        setSecondLampLit(false);
    }

}
