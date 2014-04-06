package com.inkglobal.techtest;

import com.inkglobal.techtest.clock.ClockController;
import com.inkglobal.techtest.clock.controller.BerlinClockController;
import com.inkglobal.techtest.clock.internal.DefaultClockManager;
import com.inkglobal.techtest.clock.view.text.PrintManager;
import com.inkglobal.techtest.clock.view.text.TextBerlinClockView;

/**
 * Entry point to the application. Instantiates a {@link BerlinClockController} with a
 * {@link TextBerlinClockView}.
 *
 * @author Kieran Rafferty
 */
public class BerlinClock
{
    /**
     * Instantiates a {@link BerlinClockController} with a {@link TextBerlinClockView}. Prints some
     * test cases to the console.
     *
     * @param args
     *            Not used.
     */
    public static void main(String[] args)
    {
        final PrintManager printManager = new PrintManager()
        {
            @Override
            public void println(String text)
            {
                System.out.println(text);
            }

        };

        ClockController clockController = new BerlinClockController(new TextBerlinClockView(
                printManager), new DefaultClockManager());

        System.out.println("Input       Result");
        System.out.print("00:00:00");
        clockController.setHour(0);
        clockController.setMinute(0);
        clockController.setSecond(0);

        clockController.redraw();

        System.out.print("13:17:01");
        clockController.setHour(13);
        clockController.setMinute(17);
        clockController.setSecond(1);

        clockController.redraw();

        System.out.print("23:59:59");
        clockController.setHour(23);
        clockController.setMinute(59);
        clockController.setSecond(59);

        clockController.redraw();

        System.out.print("24:00:00");
        clockController.setMinute(00);
        clockController.setSecond(00);
        clockController.setHour(24);

        clockController.redraw();
    }
}
