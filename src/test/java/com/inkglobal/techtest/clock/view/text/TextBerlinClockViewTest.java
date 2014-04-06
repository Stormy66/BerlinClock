package com.inkglobal.techtest.clock.view.text;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.inkglobal.techtest.clock.controller.BerlinClockController;
import com.inkglobal.techtest.clock.view.BerlinClockView;
import com.inkglobal.techtest.util.ExceptionTester;

/**
 * Tests for the {@link TextBerlinClockView}.
 *
 * @author Kieran Rafferty
 */
public class TextBerlinClockViewTest
{
    private static final String CANNOT_BE_NULL_MESSAGE = "%s cannot be null.";
    private static final String INDEX_EXCEEDS_ROW_SIZE = "The specified index exceeds the size of the row";
    private static final String ROW_NOT_INITIALISED = "The row has not been initialised";
    private MockPrintManager mockPrintManager;
    private BerlinClockView clockView;

    @Before
    public void setup()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);
    }

    @Test
    public void testConstructorWithNullPrintManager()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                BerlinClockView clockView = new TextBerlinClockView(null);
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_NULL_MESSAGE, "printManager"));
    }

    @Test
    public void testCreateRowOne()
    {
        int rowNumber = 1;
        int rowSize = 4;
        clockView.createHourRowOne(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().size(), equalTo(6));
        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOO"));

        mockPrintManager.clear();
        assertThat(mockPrintManager.getStrings().size(), equalTo(0));

        rowSize++;
        clockView.createHourRowOne(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOOO"));
    }

    @Test
    public void testCreateRowTwo()
    {
        int rowNumber = 2;
        int rowSize = 4;
        clockView.createHourRowTwo(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().size(), equalTo(6));
        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOO"));

        mockPrintManager.clear();
        assertThat(mockPrintManager.getStrings().size(), equalTo(0));

        rowSize++;
        clockView.createHourRowTwo(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOOO"));
    }

    @Test
    public void testCreateRowThree()
    {
        int rowNumber = 3;
        int rowSize = 4;
        clockView.createMinuteRowOne(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().size(), equalTo(6));
        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOO"));

        mockPrintManager.clear();
        assertThat(mockPrintManager.getStrings().size(), equalTo(0));

        rowSize++;
        clockView.createMinuteRowOne(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOOO"));
    }

    @Test
    public void testCreateRowFour()
    {
        int rowNumber = 4;
        int rowSize = 4;
        clockView.createMinuteRowTwo(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().size(), equalTo(6));
        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOO"));

        mockPrintManager.clear();
        assertThat(mockPrintManager.getStrings().size(), equalTo(0));

        rowSize++;
        clockView.createMinuteRowTwo(rowSize);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(rowNumber).trim(), equalTo("OOOOO"));
    }

    @Test
    public void testSetSecondLampLit()
    {
        clockView.redraw();
        assertThat(mockPrintManager.getStrings().get(0).trim(), equalTo("O"));

        mockPrintManager.clear();

        clockView.setSecondLampLit(true);
        clockView.redraw();
        assertThat(mockPrintManager.getStrings().get(0).trim(), equalTo("Y"));

        mockPrintManager.clear();

        clockView.setSecondLampLit(false);
        clockView.redraw();
        assertThat(mockPrintManager.getStrings().get(0).trim(), equalTo("O"));
    }

    @Test
    public void testSetHourRowTwoIndexLit()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        clockView.createHourRowTwo(4);
        clockView.setHourRowTwoIndexLit(1);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(2).trim(), equalTo("ROOO"));

        mockPrintManager.clear();

        clockView.setHourRowTwoIndexLit(3);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(2).trim(), equalTo("RORO"));

        mockPrintManager.clear();

        clockView.setHourRowTwoIndexLit(0);
        clockView.redraw();
        assertThat(mockPrintManager.getStrings().get(2).trim(), equalTo("OOOO"));
    }

    @Test
    public void testSetHourRowTwoIndexWithNoRow()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setHourRowTwoIndexLit(3);
            }
        };
        exceptionTester.testExceptionThrown(IllegalStateException.class,
                ROW_NOT_INITIALISED);
    }

    @Test
    public void testSetHourRowTwoIndexOutOfBounds()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);
        clockView.createHourRowTwo(4);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setHourRowTwoIndexLit(5);
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                INDEX_EXCEEDS_ROW_SIZE);
    }

    @Test
    public void testSetMinuteRowOneIndexLit()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        clockView.createMinuteRowOne(4);
        clockView.setMinuteRowOneIndexLit(1, false);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(3).trim(), equalTo("YOOO"));

        mockPrintManager.clear();

        clockView.setMinuteRowOneIndexLit(3, true);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(3).trim(), equalTo("YORO"));

        mockPrintManager.clear();

        clockView.setMinuteRowOneIndexLit(0, false);
        clockView.redraw();
        assertThat(mockPrintManager.getStrings().get(3).trim(), equalTo("OOOO"));
    }

    @Test
    public void testSetMinuteRowOneIndexWithNoRow()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setMinuteRowOneIndexLit(3, false);
            }
        };
        exceptionTester.testExceptionThrown(IllegalStateException.class,
                ROW_NOT_INITIALISED);
    }

    @Test
    public void testSetMinuteRowOneIndexOutOfBounds()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);
        clockView.createHourRowOne(4);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setHourRowOneIndexLit(5);
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                INDEX_EXCEEDS_ROW_SIZE);
    }

    @Test
    public void testSetMinuteRowTwoIndexLit()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        clockView.createMinuteRowTwo(4);
        clockView.setMinuteRowTwoIndexLit(1);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(4).trim(), equalTo("YOOO"));

        mockPrintManager.clear();

        clockView.setMinuteRowTwoIndexLit(3);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(4).trim(), equalTo("YOYO"));

        mockPrintManager.clear();

        clockView.setMinuteRowTwoIndexLit(0);
        clockView.redraw();
        assertThat(mockPrintManager.getStrings().get(4).trim(), equalTo("OOOO"));
    }

    @Test
    public void testSetMinuteRowTwoIndexWithNoRow()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setMinuteRowTwoIndexLit(3);
            }
        };
        exceptionTester.testExceptionThrown(IllegalStateException.class,
                ROW_NOT_INITIALISED);
    }

    @Test
    public void testSetMinuteRowTwoIndexOutOfBounds()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);
        clockView.createMinuteRowTwo(4);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setMinuteRowTwoIndexLit(5);
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                INDEX_EXCEEDS_ROW_SIZE);
    }

    @Test
    public void testSetHourRowOneIndexLit()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        clockView.createHourRowOne(4);
        clockView.setHourRowOneIndexLit(1);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(1).trim(), equalTo("ROOO"));

        mockPrintManager.clear();

        clockView.setHourRowOneIndexLit(3);
        clockView.redraw();

        assertThat(mockPrintManager.getStrings().get(1).trim(), equalTo("RORO"));

        mockPrintManager.clear();

        clockView.setHourRowOneIndexLit(0);
        clockView.redraw();
        assertThat(mockPrintManager.getStrings().get(1).trim(), equalTo("OOOO"));
    }

    @Test
    public void testSetHourRowOneIndexWithNoRow()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setHourRowOneIndexLit(3);
            }
        };
        exceptionTester.testExceptionThrown(IllegalStateException.class,
                ROW_NOT_INITIALISED);
    }

    @Test
    public void testSetHourRowOneIndexOutOfBounds()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);
        clockView.createHourRowOne(4);

        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                clockView.setHourRowOneIndexLit(5);
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                INDEX_EXCEEDS_ROW_SIZE);
    }

    public void testReset()
    {
        mockPrintManager = new MockPrintManager();
        clockView = new TextBerlinClockView(mockPrintManager);
        clockView.createHourRowOne(4);
        clockView.createHourRowTwo(4);
        clockView.createMinuteRowOne(11);
        clockView.createMinuteRowTwo(4);

        clockView.setSecondLampLit(true);

        for(int i = 1; i <= 4; i++)
        {
            clockView.setHourRowOneIndexLit(i);
            clockView.setMinuteRowTwoIndexLit(i);
            clockView.setHourRowTwoIndexLit(i);
        }
        for(int i = 1; i <= 11; i++)
        {
            clockView.setMinuteRowOneIndexLit(i, false);
        }

        clockView.redraw();

        List<String> results = mockPrintManager.getStrings();

        assertThat(results.get(0).trim(), equalTo("Y"));
        assertThat(results.get(1).trim(), equalTo("RRRR"));
        assertThat(results.get(2).trim(), equalTo("RRRR"));
        assertThat(results.get(3).trim(), equalTo("RRRRRRRRRRR"));
        assertThat(results.get(4).trim(), equalTo("RRRR"));
        
        mockPrintManager.clear();
        clockView.reset();
        clockView.redraw();
        
        results = mockPrintManager.getStrings();

        assertThat(results.get(0).trim(), equalTo("O"));
        assertThat(results.get(1).trim(), equalTo("OOOO"));
        assertThat(results.get(2).trim(), equalTo("OOOO"));
        assertThat(results.get(3).trim(), equalTo("OOOOOOOOOOO"));
        assertThat(results.get(4).trim(), equalTo("OOOO"));
    }

    /**
     * Implementation of the {@link PrintManager} for testing purposes.
     *
     * @author Kieran Rafferty
     */
    public static class MockPrintManager implements PrintManager
    {
        List<String> strings = new ArrayList<String>();

        @Override
        public void println(String text)
        {
            strings.add(text);
        }

        /**
         * @return a list of the strings that have so far been passed to the
         *         {@link #println(String)} method.
         */
        public List<String> getStrings()
        {
            return Collections.unmodifiableList(strings);
        }

        public void clear()
        {
            strings.clear();
        }
    }
}
