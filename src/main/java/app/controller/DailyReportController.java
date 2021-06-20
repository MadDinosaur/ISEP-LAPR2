package app.controller;

import app.domain.adapter.ExternalModuleNhsReport;
import app.domain.adapter.ExternalModuleNhsReportAdapter;
import app.domain.model.Company;
import app.configs.Configs;
import app.domain.model.WriteReport;
import com.sun.prism.shader.DrawEllipse_LinearGradient_REFLECT_AlphaTest_Loader;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private final Company company = App.getInstance().getCompany();
    private final ExternalModuleNhsReport nhsReport = new ExternalModuleNhsReportAdapter();
    private int historicalPoints;
    private String independentVariable;
    private String currentDay;
    private String initialDay;
    private String finalDay;


    //private WriteReport writeReport = new WriteReport();

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
        timer.schedule(new DailyReportController(), 3000, period );

        /*
        int period = 86400000; // 1 day in milliseconds
        timer.schedule(new DailyReportController(), date, period);
        */
    }

    /**
     * method triggered by the timer above
     */
    public void run() {


        Properties props = new Properties(System.getProperties());
        String intervalDates = props.getProperty("intervalDates");
        String historicalPointsString = props.getProperty("historicalPoints");
        historicalPoints = Integer.parseInt(historicalPointsString);

        //String report = writeReport.getReport();
        String abc = "xD funny momement";


        createNhsReportController.setHistoricalPoints(historicalPoints);
        createNhsReportController.setCurrentDay(currentDay);
        createNhsReportController.setInitialDay(initialDay);
        createNhsReportController.setFinalDay(finalDay);
        createNhsReportController.setIndependentVariable(independentVariable);

        System.out.println("NHS Daily Report sent");
    }
}
