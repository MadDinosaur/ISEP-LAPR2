package app.controller;

import app.domain.adapter.ExternalModuleNhsReport;
import app.domain.adapter.ExternalModuleNhsReportAdapter;
import app.domain.model.Company;
import app.domain.model.WriteReport;
import com.sun.prism.shader.DrawEllipse_LinearGradient_REFLECT_AlphaTest_Loader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class DailyReportController extends TimerTask {
    private Company company = App.getInstance().getCompany();
    private ExternalModuleNhsReport nhsreport = new ExternalModuleNhsReportAdapter();

    /**
     * timer which will trigger the creating of a daily report
     */
    public static void dailyReportTimer() {
        //timer task for daily NHS report
        //the Date and time at which you want to execute

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

        int period = 10000; //10 seconds
        timer.schedule(new DailyReportController(), 3000, period );

        /*
        int period = 86400000; // 1 day in milliseconds
        timer.schedule(new DailyReportController(), date, period );
        */
    }

    /**
     * method triggered by the timer above
     */
    public void run() {
        //new WriteReport();
        nhsreport.generateLogEvent();
        System.out.println("NHS Daily Report sent");
    }
}
