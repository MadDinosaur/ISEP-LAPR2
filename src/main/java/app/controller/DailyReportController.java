package app.controller;

import app.domain.adapter.ExternalModuleNhsReport;
import app.domain.adapter.ExternalModuleNhsReportAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class DailyReportController extends TimerTask {
    CreateNhsReportController createNhsReportController = new CreateNhsReportController();
    private final ExternalModuleNhsReport nhsReport = new ExternalModuleNhsReportAdapter();

    /**
     * timer which will trigger the creating of a daily report
     */
    public static void dailyReportTimer()  {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = dateFormatter.parse("2021-06-18 6:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Timer timer = new Timer();
        int period = 86400000; // 1 day in milliseconds
        timer.schedule(new DailyReportController(), date, period);

    }

    /**
     * method triggered by the timer above
     */
    public void run() {

        Properties props = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src\\main\\resources\\config.properties");
            props.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String historicalPointsString = props.getProperty("historicalPoints");
        String today = nhsReport.getDateAndTime();
        String[] dateComponents = today.split(" ");
        String intervalDates = props.getProperty("intervalDates");
        String[] intervalDate = intervalDates.split(" ");

        int historicalPoints = Integer.parseInt(historicalPointsString);
        String currentDay = dateComponents[0];
        String initialDay = intervalDate[0];
        String finalDay = intervalDate[1];
        String independentVariable = props.getProperty("independentVariable");

        createNhsReportController.setHistoricalPoints(historicalPoints);
        createNhsReportController.setCurrentDay(currentDay);
        createNhsReportController.setInitialDay(initialDay);
        createNhsReportController.setFinalDay(finalDay);
        createNhsReportController.setIndependentVariable(independentVariable);
        String report = createNhsReportController.makeRegression();

        nhsReport.sendReport(report);

    }
}
