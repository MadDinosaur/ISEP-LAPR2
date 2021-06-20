package app.domain.model;

import app.controller.DailyReportController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class DailyReportTimer {
    /**
     * timer which will trigger the creating of a daily report
     */
    public static void dailyReportTimer()  {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = dateFormatter.parse("2021-06-20 6:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer();
        int period = 86400000; // 1 day in milliseconds

        //long delay = 3000;

        timer.schedule(new DailyReportController(), date, period);
    }
}
