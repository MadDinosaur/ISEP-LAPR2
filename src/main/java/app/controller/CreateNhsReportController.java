package app.controller;

import app.domain.model.Company;
import app.domain.shared.Constants;

import java.util.Date;
import java.util.GregorianCalendar;

public class CreateNhsReportController {

    /**
     * Instance of company
     */
    private final Company company = Constants.company;

    /**
     * Number of days for the prevision table
     */
    private int historicalPoints;

    /**
     * Independent variable for the regression
     */
    private String independentVariable;

    /**
     * Starting day for the prevision table
     */
    private String currentDay;

    /**
     * Starting day for the regression model
     */
    private String initialDay;

    /**
     * Final day for the regression model
     */
    private String finalDay;

    private double confidence;

    private double significance;

    private String varTest;

    /**
     * Empty class constructor for the controller
     *
     */
    public CreateNhsReportController(){
    }

    /**
     * Setter for the historical points
     *
     * @param historicalPoints
     */
    public void setHistoricalPoints(int historicalPoints) {
        this.historicalPoints = historicalPoints;
    }

    /**
     * Setter for the current day
     *
     * @param currentDay
     */
    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }

    /**
     * Setter for the initial day of regression
     *
     * @param initialDay
     */
    public void setInitialDay(String initialDay) {
        this.initialDay = initialDay;
    }

    /**
     * Setter for the final day of regression
     *
     * @param finalDay
     */
    public void setFinalDay(String finalDay) {
        this.finalDay = finalDay;
    }

    /**
     * Setter for the independent variable
     *
     * @param independentVariable
     */
    public void setIndependentVariable(String independentVariable) {
        this.independentVariable = independentVariable;
    }

    public void setConfidence(int confidenceInt){
        confidence = (double) confidenceInt / 100;
    }
    public void setSignificance(int significance){
        this.significance = (double) significance / 100;
    }

    public void setVarTest(String varTest) {
        this.varTest = varTest;
    }

    /**
     * Generates a regression with the data in the class
     *
     * @return String with report
     */
    public String makeRegression(){

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

        String report = "";


        if(independentVariable.equalsIgnoreCase("multilinear")){
            report = company.makeMultiLinearRegressionReport(historicalPoints,currentDay,dateInitialDay,dateFinalDay, confidence, significance, varTest);
        } else {
            if(independentVariable.equalsIgnoreCase("mean age")){
                try {
                    company.makeSimpleLinearRegressionReport(historicalPoints,currentDay,dateInitialDay,dateFinalDay,independentVariable,confidence, significance,varTest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                if(independentVariable.equalsIgnoreCase("total tests")){
                    try {
                        company.makeSimpleLinearRegressionReport(historicalPoints,currentDay,dateInitialDay,dateFinalDay,independentVariable,confidence, significance, varTest);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return report;
    }
}
