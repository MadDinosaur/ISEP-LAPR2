package app.controller;

import app.domain.adapter.ExternalModuleNhsReport;
import app.domain.adapter.ExternalModuleNhsReportAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.TimerTask;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class DailyReportController extends TimerTask {
    CreateNhsReportController createNhsReportController = new CreateNhsReportController();
    private final ExternalModuleNhsReport nhsReport = new ExternalModuleNhsReportAdapter();

    /**
     * runnable which is triggered by the timer created at DailyReportTimer.java
     * here a daily report will be created and sent to NHS
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

        int confidenceValue = Integer.parseInt(props.getProperty("confidence"));

        int significance = Integer.parseInt(props.getProperty("significance"));

        String testVariable = props.getProperty("testVariable");
        String testVariableValue;
        if(testVariable.equalsIgnoreCase("x1")){
            testVariableValue = "1";
        }else{
            if(testVariable.equalsIgnoreCase("x2")){
                testVariableValue = "2";
            }else{
                testVariableValue = "3";
            }
        }

        createNhsReportController.setHistoricalPoints(historicalPoints);
        createNhsReportController.setCurrentDay(currentDay);
        createNhsReportController.setInitialDay(initialDay);
        createNhsReportController.setFinalDay(finalDay);
        createNhsReportController.setIndependentVariable(independentVariable);
        createNhsReportController.setConfidence(confidenceValue);
        createNhsReportController.setSignificance(significance);
        createNhsReportController.setVarTest(testVariableValue);


        String report = createNhsReportController.makeRegression();

        nhsReport.sendReport(report);
    }

}
