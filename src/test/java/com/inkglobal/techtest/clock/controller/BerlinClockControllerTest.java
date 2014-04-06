package com.inkglobal.techtest.clock.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.mockito.Mock;

import com.inkglobal.techtest.clock.AbstractClockController;
import com.inkglobal.techtest.clock.ClockManager;
import com.inkglobal.techtest.clock.TestAbstractClockController;
import com.inkglobal.techtest.clock.view.BerlinClockView;
import com.inkglobal.techtest.clock.view.configuration.ClockConfiguration;
import com.inkglobal.techtest.mocks.MockBerlinClockController;
import com.inkglobal.techtest.mocks.MockBerlinClockView;
import com.inkglobal.techtest.mocks.MockClockManager;
import com.inkglobal.techtest.util.ExceptionTester;

/**
 * Tests for the {@link BerlinClockController}.
 *
 * @author Kieran Rafferty
 */
public class BerlinClockControllerTest extends TestAbstractClockController
{
    private static final String CANNOT_BE_NULL_MESSAGE = "%s cannot be null.";

    @Mock
    BerlinClockView mockView = mock(BerlinClockView.class);
    @Mock
    private static ClockManager mockManager = mock(ClockManager.class);

    private BerlinClockController controller;
    private static final int[] time = { 1, 2, 3 };

    @Before
    public void setup()
    {
        when(mockManager.getClockConfiguration()).thenReturn(
                new ClockConfiguration.Builder().build());

        when(mockManager.getTime()).thenReturn(time);
        controller = new BerlinClockController(mockView, mockManager);
    }

    @Test
    public void testCreateBerlinClockControllerWithNullView()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                BerlinClockController controller = new BerlinClockController(null, mockManager);
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_NULL_MESSAGE, "view"));
    }

    @Test
    public void testCreateBerlinClockControllerWithNullManager()
    {
        ExceptionTester exceptionTester = new ExceptionTester()
        {
            @Override
            public void check()
            {
                BerlinClockController controller = new BerlinClockController(mockView, null);
            }
        };
        exceptionTester.testExceptionThrown(IllegalArgumentException.class,
                String.format(CANNOT_BE_NULL_MESSAGE, "manager"));
    }

    @Test
    public void testViewIsCreatedWithConfigurationParameters()
    {
        int[] expectedRowSizes = { 9, 8, 7, 6 };

        final int[] rowSizes = new int[4];
        BerlinClockView mockView = new MockBerlinClockView()
        {
            @Override
            public void createHourRowOne(int size)
            {
                rowSizes[0] = size;
            }

            @Override
            public void createHourRowTwo(int size)
            {
                rowSizes[1] = size;
            }

            @Override
            public void createMinuteRowOne(int size)
            {
                rowSizes[2] = size;
            }

            @Override
            public void createMinuteRowTwo(int size)
            {
                rowSizes[3] = size;
            }
        };

        when(mockManager.getClockConfiguration()).thenReturn(
                new ClockConfiguration.Builder().setRowOneLength(expectedRowSizes[0])
                        .setRowTwoLength(expectedRowSizes[1])
                        .setRowThreeLength(expectedRowSizes[2])
                        .setRowFourLength(expectedRowSizes[3]).build());

        new BerlinClockController(mockView, mockManager);

        for (int i = 0; i < expectedRowSizes.length; i++)
        {
            assertThat(rowSizes[0], equalTo(expectedRowSizes[0]));
        }
    }

    @Test
    public void testConstructorPassesManagerTime()
    {
        final boolean[] secondsLampLit = new boolean[1];

        final int[] setRowOneCallCount = new int[1];
        final int[] setRowTwoCallCount = new int[1];
        final int[] setRowThreeCallCount = new int[1];
        final int[] setRowFourCallCount = new int[1];

        BerlinClockView mockView = new MockBerlinClockView()
        {
            @Override
            public void setSecondLampLit(boolean lit)
            {
                secondsLampLit[0] = lit;
            }

            @Override
            public void setHourRowOneIndexLit(int index)
            {
                setRowOneCallCount[0] = setRowOneCallCount[0] + 1;
            }

            @Override
            public void setHourRowTwoIndexLit(int index)
            {
                setRowTwoCallCount[0] = setRowTwoCallCount[0] + 1;
            }

            @Override
            public void setMinuteRowOneIndexLit(int index, boolean useAltColor)
            {
                setRowThreeCallCount[0] = setRowThreeCallCount[0] + 1;
            }

            @Override
            public void setMinuteRowTwoIndexLit(int index)
            {
                setRowFourCallCount[0] = setRowFourCallCount[0] + 1;
            }

        };

        int[] time = { 12, 33, 16 };
        when(mockManager.getTime()).thenReturn(time);

        new BerlinClockController(mockView, mockManager);

        assertThat(setRowOneCallCount[0], equalTo(2));
        assertThat(setRowTwoCallCount[0], equalTo(2));
        assertThat(setRowThreeCallCount[0], equalTo(6));
        assertThat(setRowFourCallCount[0], equalTo(3));

        assertThat(secondsLampLit[0], equalTo(true));
    }

    @Test
    public void testControllerConstructorPassesActionListenerToManager()
    {
        MockClockManager mockManager = new MockClockManager();

        new BerlinClockController(mockView, mockManager);
        assertThat(mockManager.getTimesAddTimerActionCalled(), equalTo(1));
    }

    @Test
    public void testCalculateDefaultHour()
    {
        int[] result = controller.calculateHour(15);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(0));

        result = controller.calculateHour(16);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(1));

        result = controller.calculateHour(17);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(2));

        result = controller.calculateHour(18);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(3));

        result = controller.calculateHour(19);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(4));

        result = controller.calculateHour(20);
        assertThat(result[0], equalTo(4));
        assertThat(result[1], equalTo(0));
    }

    @Test
    public void testCalculateCustomHour()
    {
        ClockManager mockManager = mock(ClockManager.class);
        when(mockManager.getClockConfiguration()).thenReturn(
                new ClockConfiguration.Builder().setRowOneLength(6).setRowTwoLength(3).build());
        when(mockManager.getTime()).thenReturn(time);
        BerlinClockController controller = new BerlinClockController(mockView, mockManager);

        int[] result = controller.calculateHour(16);
        assertThat(result[0], equalTo(4));
        assertThat(result[1], equalTo(0));

        result = controller.calculateHour(17);
        assertThat(result[0], equalTo(4));
        assertThat(result[1], equalTo(1));

        result = controller.calculateHour(18);
        assertThat(result[0], equalTo(4));
        assertThat(result[1], equalTo(2));

        result = controller.calculateHour(19);
        assertThat(result[0], equalTo(4));
        assertThat(result[1], equalTo(3));

        result = controller.calculateHour(20);
        assertThat(result[0], equalTo(5));
        assertThat(result[1], equalTo(0));
    }

    @Test
    public void testCalculateDefaultMinute()
    {
        when(mockManager.getClockConfiguration()).thenReturn(
                new ClockConfiguration.Builder().build());
        when(mockManager.getTime()).thenReturn(time);
        int[] result = controller.calculateMinute(15);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(0));

        result = controller.calculateMinute(16);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(1));

        result = controller.calculateMinute(17);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(2));

        result = controller.calculateMinute(18);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(3));

        result = controller.calculateMinute(19);
        assertThat(result[0], equalTo(3));
        assertThat(result[1], equalTo(4));

        result = controller.calculateMinute(20);
        assertThat(result[0], equalTo(4));
        assertThat(result[1], equalTo(0));
    }

    @Test
    public void testCalculateCustomMinute()
    {
        ClockManager mockManager = mock(ClockManager.class);
        when(mockManager.getClockConfiguration()).thenReturn(
                new ClockConfiguration.Builder().setRowThreeLength(9).setRowFourLength(2).build());
        when(mockManager.getTime()).thenReturn(time);
        BerlinClockController controller = new BerlinClockController(mockView, mockManager);

        int[] result = controller.calculateMinute(15);
        assertThat(result[0], equalTo(5));
        assertThat(result[1], equalTo(0));

        result = controller.calculateMinute(16);
        assertThat(result[0], equalTo(5));
        assertThat(result[1], equalTo(1));

        result = controller.calculateMinute(17);
        assertThat(result[0], equalTo(5));
        assertThat(result[1], equalTo(2));

        result = controller.calculateMinute(18);
        assertThat(result[0], equalTo(6));
        assertThat(result[1], equalTo(0));
    }

    @Test
    public void testSetHour()
    {
        MockClockManager mockManager = new MockClockManager();
        MockBerlinClockView mockView = new MockBerlinClockView();
        int[] midnight = { 0, 0, 0 };
        mockManager.setTime(midnight);

        BerlinClockController berlinClockController = new BerlinClockController(mockView, mockManager);
        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(true));

        berlinClockController.setHour(16);

        assertThat(mockView.getRowOneIndex(), equalTo(3));
        assertThat(mockView.getRowTwoIndex(), equalTo(1));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(true));

        berlinClockController.setHour(16);
    }

    @Test
    public void testSetMinute()
    {
        MockClockManager mockManager = new MockClockManager();
        MockBerlinClockView mockView = new MockBerlinClockView();
        int[] midnight = { 0, 0, 0 };
        mockManager.setTime(midnight);

        BerlinClockController berlinClockController = new BerlinClockController(mockView, mockManager);
        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(true));

        berlinClockController.setMinute(16);

        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(3));
        assertThat(mockView.getRowFourIndex(), equalTo(1));
        assertThat(mockView.isSecondLampLit(), equalTo(true));
    }

    @Test
    public void testSetSecond()
    {
        MockClockManager mockManager = new MockClockManager();
        MockBerlinClockView mockView = new MockBerlinClockView();
        int[] midnight = { 0, 0, 0 };
        mockManager.setTime(midnight);

        BerlinClockController berlinClockController = new BerlinClockController(mockView, mockManager);
        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(true));

        berlinClockController.setSecond(1);

        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(true));

        berlinClockController.setSecond(2);

        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(false));

        berlinClockController.setSecond(3);

        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(false));
    }

    @Test
    public void testIncrement() throws InterruptedException
    {
        MockClockManager mockManager = new MockClockManager();
        MockBerlinClockView mockView = new MockBerlinClockView();
        int[] midnight = { 0, 0, 0 };
        mockManager.setTime(midnight);

        new BerlinClockController(mockView, mockManager);

        assertThat(mockView.getRowOneIndex(), equalTo(0));
        assertThat(mockView.getRowTwoIndex(), equalTo(0));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(true));

        for (int i = 1; i < 86400; i++)
        {
            mockManager.notifyListeners();
            // tests seconds
            assertThat(mockView.isSecondLampLit(), equalTo(i / 2 % 2 == 0));
            // tests row four
            assertThat(mockView.getRowFourIndex(), equalTo((i / 60) % 5));
            // tests row three
            assertThat(mockView.getRowThreeIndex(), equalTo((i / 60 / 5) % 12));
            assertThat(mockView.isRowThreeUsingAltColor(), equalTo(mockView.getRowThreeIndex()-1 != 0
                    && (mockView.getRowThreeIndex()) % 3 == 0));
            // tests row two
            assertThat(mockView.getRowTwoIndex(), equalTo((i / 60 / 60) % 5));
            // test row one
            assertThat(mockView.getRowOneIndex(), equalTo((i / 60 / 60 / 5) % 5));
        }

        mockManager.notifyListeners();

        assertThat(mockView.getRowOneIndex(), equalTo(4));
        assertThat(mockView.getRowTwoIndex(), equalTo(4));
        assertThat(mockView.getRowThreeIndex(), equalTo(0));
        assertThat(mockView.getRowFourIndex(), equalTo(0));
        assertThat(mockView.isSecondLampLit(), equalTo(true));
    }

    @Test
    public void testIncrementAcrossMidnight()
    {
        MockClockManager mockManager = new MockClockManager();
        MockBerlinClockController controller = new MockBerlinClockController(mockView, mockManager);
        controller.setHour(23);
        controller.setMinute(59);
        controller.setSecond(59);

        assertThat(controller.getHour(), equalTo(23));
        assertThat(controller.getMinute(), equalTo(59));
        assertThat(controller.getSecond(), equalTo(59));
        
        mockManager.notifyListeners();
        
        assertThat(controller.getHour(), equalTo(24));
        assertThat(controller.getMinute(), equalTo(0));
        assertThat(controller.getSecond(), equalTo(0));

        mockManager.notifyListeners();
        
        assertThat(controller.getHour(), equalTo(0));
        assertThat(controller.getMinute(), equalTo(0));
        assertThat(controller.getSecond(), equalTo(1));
    }

    @Override
    public AbstractClockController createController()
    {
        return new BerlinClockController(mockView, mockManager);
    }
}
