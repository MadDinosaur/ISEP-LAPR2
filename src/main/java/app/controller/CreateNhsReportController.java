package app.controller;

import app.domain.model.Company;

import java.util.Date;
import java.util.GregorianCalendar;

public class CreateNhsReportController {
    private final Company company = App.getInstance().getCompany();
    private int historicalPoints;
    private String independentVariable;
    private String currentDay;
    private String initialDay;
    private String finalDay;

    public CreateNhsReportController(){
    }

    public void setHistoricalPoints(int historicalPoints) {
        this.historicalPoints = historicalPoints;
    }
    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }
    public void setInitialDay(String initialDay) {
        this.initialDay = initialDay;
    }
    public void setFinalDay(String finalDay) {
        this.finalDay = finalDay;
    }
    public void setIndependentVariable(String independentVariable) {
        this.independentVariable = independentVariable;
    }
    public void makeRegression(){

        String[] dateComponentsFinal = finalDay.split("/");
        int dayFinal = Integer.parseInt(dateComponentsFinal[0]);
        int monthFinal = Integer.parseInt(dateComponentsFinal[1]);
        int yearFinal = Integer.parseInt(dateComponentsFinal[2]);
        Date dateFinalDay = new GregorianCalendar(yearFinal, monthFinal, dayFinal).getTime();

        String[] dateComponents = initialDay.split("/");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);
        Date dateInitialDay = new GregorianCalendar(year, month, day).getTime();

        if(independentVariable.equalsIgnoreCase("multilinear")){
            company.makeMultiLinearRegressionReport(historicalPoints,currentDay,dateInitialDay,dateFinalDay);
        }else{
            if(independentVariable.equalsIgnoreCase("mean age")){
                try {
                    company.makeSimpleLinearRegressionReport(historicalPoints,currentDay,dateInitialDay,dateFinalDay,independentVariable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                if(independentVariable.equalsIgnoreCase("total tests")){
                    try {
                        company.makeSimpleLinearRegressionReport(historicalPoints,currentDay,dateInitialDay,dateFinalDay,independentVariable);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }
}
