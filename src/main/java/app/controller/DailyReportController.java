package app.controller;

import app.domain.adapter.ExternalModuleNhsReport;
import app.domain.adapter.ExternalModuleNhsReportAdapter;
import app.domain.model.Company;
import app.configs.Configs;
import app.domain.model.WriteReport;
import com.sun.prism.shader.DrawEllipse_LinearGradient_REFLECT_AlphaTest_Loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class DailyReportController extends TimerTask {

    Company company = App.getInstance().getCompany();
    CreateNhsReportController createNhsReportController = new CreateNhsReportController();

    private final ExternalModuleNhsReport nhsReport = new ExternalModuleNhsReportAdapter();
    private WriteReport writeReport;

    /**
     * timer which will trigger the creating of a daily report
     */
    public static void dailyReportTimer()  {

        /* Ativar isto e o resto em baixo para usar os tempos corretos

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = dateFormatter.parse("2021-06-18 6:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
         */

        Timer timer = new Timer();

        int period = 10000;//10 seconds
        timer.schedule(new DailyReportController(), 1000, period );
        /*
        int period = 86400000; // 1 day in milliseconds
        timer.schedule(new DailyReportController(), date, period);
        */
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
