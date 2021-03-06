package app.domain.model;

import app.domain.store.TestStore;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.math3.distribution.TDistribution;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class WriteReport implements CharSequence {

    /**
     * Statistic value for R^2
     */
    double rSquared;

    /**
     * Statistic value for R^2 Adjusted
     */
    double r2Adjusted;

    /**
     * Statistic value for R
     */
    double r;

    /**
     * Statistic value for square sum of Regression
     */
    private final double sqR;

    /**
     * Statistic value for square sum of Regression Error
     */
    private final double sqE;

    /**
     * Statistic value for the total square sum
     */
    private final double sqT;

    /**
     * Statistic value for the average square sum of Regression
     */
    private final double sqRAverage;

    /**
     * Statistic value for the average square sum of Regression Error
     */
    private final double sqEAverage;

    /**
     * Statistic value of F
     */
    private final double fTest;

    /**
     * Number of degrees of freedom of Regression
     */
    private final int degreesOfFreedom;

    /**
     * Value of the first regressor
     */
    double xa;

    /**
     * Value of the second regressor (only needed in Multiple Linear Regression)
     */
    double xb;

    /**
     * Value of the intercept in x=0
     */
    double intercept;

    /**
     *  Critical value for confidence intervals
     */
    double critical;

    /**
     *  Array of values for the regression
     */
    double[] x1 ;

    /**
     *  Second array of values for the multiple linear regression
     */
    double[] x2 ;

    /**
     *  Array of values of the dependent variable for the regression
     */
    double[] y;

    /**
     *  String generated by the constuctor
     */
    String stringToReport;

    double confidence;



    /**
     * Write Report class constructor for Multiple Linear Regressions
     *
     * @param testStore         Company's testStore
     * @param historicalPoints  Number of days for the prevision table
     * @param currentDay        Day start of prevision table
     * @param startRegression   Day start of the regression model
     * @param finishRegression  Day finish of the regression model
     */
    public WriteReport(TestStore testStore, int historicalPoints, String currentDay, Date startRegression, Date finishRegression, double confidence, double significance, String varTest) {

        long diffInDays =  (finishRegression.getTime() - startRegression .getTime());
        int daysOfRegression = (int) TimeUnit.DAYS.convert(diffInDays, TimeUnit.MILLISECONDS);

        x1 = new double[daysOfRegression+1];
        x2 = new double[daysOfRegression+1];
        y = new double[daysOfRegression+1];
        String[] dateComponents = currentDay.split("/");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);
        Date dateCurrentDay = new GregorianCalendar(year, month, day).getTime();

        Date dayOfTableToMakeRegression = finishRegression;
        dayOfTableToMakeRegression = DateUtils.addDays(dayOfTableToMakeRegression, 1);
        int i;
        for (i = 0; i <= daysOfRegression; i++) {
            dayOfTableToMakeRegression = DateUtils.addDays(dayOfTableToMakeRegression, -1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dayOfTableToMakeRegression);
            int dayOfCalendar = calendar.get(Calendar.DAY_OF_MONTH);
            int monthOfCalendar = calendar.get(Calendar.MONTH);
            int yearOfCalendar = calendar.get(Calendar.YEAR);
            Date newDate = new Date(dayOfCalendar,monthOfCalendar,yearOfCalendar);
            String dateInString;
            if(dayOfCalendar>9){
                dateInString = ""+dayOfCalendar;
            }else{
                dateInString = "0"+dayOfCalendar;
            }
            if(monthOfCalendar>9){
                dateInString = dateInString +"/" + monthOfCalendar;
            }else{
                dateInString = dateInString + "/0"+ monthOfCalendar;
            }
            dateInString = dateInString+ "/" + yearOfCalendar;

            if (newDate.getDay() == 0) {
                i--;
                continue;
            }
            x1[i] = testStore.getNumberOfTestsPerformed(dateInString);
            x2[i] = testStore.getAverageAgeOfClient(dateInString);
            y[i] = testStore.getNumberOfPositiveCasesPerDay(dateInString);
        }


        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x1, x2, y);
        multiLinearRegression.setConfidence((1+confidence)/2);
        xa = multiLinearRegression.getX1();

        xb = multiLinearRegression.getX2();
        intercept = multiLinearRegression.getIntercept();
        degreesOfFreedom = multiLinearRegression.getDegreesOfFreedom();
        sqR = multiLinearRegression.getSqR();
        sqE = multiLinearRegression.getSqE();
        sqT = sqE + sqR;
        sqEAverage = multiLinearRegression.getSqEAverage();
        sqRAverage = multiLinearRegression.getSqRAverage();
        fTest = multiLinearRegression.getFTest();
        rSquared = multiLinearRegression.getRSquared();
        r2Adjusted = multiLinearRegression.getRSquaredAdjusted();
        r = multiLinearRegression.getR();


        double t_obs;
        if(varTest.equalsIgnoreCase("1")){
            t_obs = xa / Math.sqrt(sqEAverage*multiLinearRegression.xTransposeX1());
        }else{
            if(varTest.equalsIgnoreCase("2")){
                t_obs = xb / Math.sqrt(sqEAverage*multiLinearRegression.xTransposeX2());
            }else{
                t_obs = intercept / Math.sqrt(sqEAverage*multiLinearRegression.xTransposeX0());
            }
        }

        String rejection;
        TDistribution tDistribution = new TDistribution(degreesOfFreedom-2);
        double coefientCritical = tDistribution.cumulativeProbability((1+significance)/2);
        if(t_obs > coefientCritical){
            rejection = "Reject H0";
        }else{
            rejection = "No reject H0";
        }
        int confidence100 = (int) (confidence *100);

        critical = multiLinearRegression.getCritical();

        StringBuilder stringToBuild = new StringBuilder("The regression model fitted using data from the interval\n" +
                "^y = " + xa + "x1 + " + xb + "x2 +" + intercept + "\n" +
                "\n" +
                "Other statistics\n" +
                "R2 = " + rSquared + "\n" +
                "R2adjusted = " + r2Adjusted + "\n" +
                "R = " + r + "\n" +
                "\n" +
                "Hypothesis tests for regression coefficients\n" +
                "HO:b=0 (a=0) H1: b<>0 (a<>0)\n" +
                "t_obs = " + t_obs + "\n" +
                "Decision: "+ "\n" +
                rejection + "\n" +
                "\n" +
                "\n" +
                "Significance model with Anova\n" +
                "H0: b=0  H1:b<>0\n" +
                "\t\t            SS\t       df\t\t     MS\t\t     F\n" +
                "Regression\t" + sqR + "\t" + "2" + "\t" + sqRAverage + "\t" + fTest + "\n" +  //simple /multi
                "Residual\t" + sqE + "\t" + (degreesOfFreedom - 2) + "\t " + sqEAverage + "\n" +
                "Total\t\t" + sqT + "\t" + degreesOfFreedom + "\n" +
                "\n" +
                "Decision: f  \n" +
                "0 > f0.05,(2. " + (degreesOfFreedom - 2) + ")=" + critical + "\n");
        if (fTest > critical) {
            stringToBuild.append("Reject H0\n" +                                      //reject or not
                    "The regression model is significant.\n");
        } else {
            stringToBuild.append("No reject H0\n" +
                    "The regression model is not significant.\n");
        }
        stringToBuild.append("\n" +
                "// Prediction values\n" +
                "\n" +
                "Date                Number of OBSERVED positive cases           Number of ESTIMATED/EXPECTED positive cases         \t\t"+confidence100+ "% intervals\n");
        Date dayOfTable = dateCurrentDay;
        int index;
        for (index = 1; index <= historicalPoints; index++) {
            dayOfTable = DateUtils.addDays(dayOfTable, -1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dayOfTable);
            int dayOfCalendar = calendar.get(Calendar.DAY_OF_MONTH);
            int monthOfCalendar = calendar.get(Calendar.MONTH);
            int yearOfCalendar = calendar.get(Calendar.YEAR);
            Date newDate = new Date(dayOfCalendar,monthOfCalendar,yearOfCalendar);
            String dateInString;
            if(dayOfCalendar>9){
                dateInString = ""+dayOfCalendar;
            }else{
                dateInString = "0"+dayOfCalendar;
            }
            if(monthOfCalendar>9){
                dateInString = dateInString +"/" + monthOfCalendar;
            }else{
                dateInString = dateInString + "/0"+ monthOfCalendar;
            }
            dateInString = dateInString+ "/" + yearOfCalendar;

            //String pattern = "dd/MM/yyyy";
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
           //String dayOfTableInString = simpleDateFormat.format(dayOfTable);

            if (newDate.getDay() == 0) {
                index--;

            } else {
                double critical = multiLinearRegression.getPrevisionforY(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString));
                stringToBuild.append((dateInString + "                    " + testStore.getNumberOfPositiveCasesPerDay(dateInString) + "                                      " + multiLinearRegression.getPrevisionforY(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString)) + "                                      " + (critical - multiLinearRegression.getCriticalValue(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString))) + "-" + (critical + multiLinearRegression.getCriticalValue(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString)))) + "\n");
            }
        }
        stringToReport = stringToBuild.toString();
    }

    /**
     * Write Report class constructor for Simple Linear Regressions
     *
     *@param testStore              Company's testStore
     * @param historicalPoints      Number of days for the prevision table
     * @param currentDay            Day start of prevision table
     * @param startRegression       Day start of the regression model
     * @param finishRegression      Day finish of the regression model
     * @param independentVariable   Independent variable for the regression model
     */
    public WriteReport(TestStore testStore, int historicalPoints, String currentDay, Date startRegression, Date finishRegression, String independentVariable, double confidence, double significance, String varTest) throws Exception {
        this.confidence = confidence;
        long diffInDays =  (finishRegression.getTime() - startRegression .getTime());
        int daysOfRegression = (int) TimeUnit.DAYS.convert(diffInDays, TimeUnit.MILLISECONDS);

        x1 = new double[daysOfRegression+1];
        x2 = new double[daysOfRegression+1];
        y = new double[daysOfRegression+1];
        String[] dateComponents = currentDay.split("/");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);
        Date dateCurrentDay = new GregorianCalendar(year, month, day).getTime();

        Date dayOfTableToMakeRegression = finishRegression;
        dayOfTableToMakeRegression = DateUtils.addDays(dayOfTableToMakeRegression, 1);
        int i;
        for (i = 0; i <= daysOfRegression; i++) {
            dayOfTableToMakeRegression = DateUtils.addDays(dayOfTableToMakeRegression, -1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dayOfTableToMakeRegression);
            int dayOfCalendar = calendar.get(Calendar.DAY_OF_MONTH);
            int monthOfCalendar = calendar.get(Calendar.MONTH);
            int yearOfCalendar = calendar.get(Calendar.YEAR);

            Date newDate = new Date(dayOfCalendar,monthOfCalendar,yearOfCalendar);
            String dateInString;
            if(dayOfCalendar>9){
                dateInString = ""+dayOfCalendar;
            }else{
                dateInString = "0"+dayOfCalendar;
            }
            if(monthOfCalendar>9){
                dateInString = dateInString +"/" + monthOfCalendar;
            }else{
                dateInString = dateInString + "/0"+ monthOfCalendar;
            }
            dateInString = dateInString+ "/" + yearOfCalendar;

            if (newDate.getDay() == 0) {
                i--;
                continue;
            }
            x1[i] = testStore.getNumberOfTestsPerformed(dateInString);
            x2[i] = testStore.getAverageAgeOfClient(dateInString);
            y[i] = testStore.getNumberOfPositiveCasesPerDay(dateInString);
        }

        LinearRegression linearRegression;
        if(independentVariable.equalsIgnoreCase("mean age")){
             linearRegression = new LinearRegression(x2,y);
        }else{
            if(independentVariable.equalsIgnoreCase("total tests")){
                linearRegression = new LinearRegression(x1,y);
            }else{
                throw new Exception("Invalid variable");
            }
        }

        xa = linearRegression.slope();
        intercept = linearRegression.intercept();
        degreesOfFreedom = linearRegression.getDegreesOfFreedom();
        sqR = linearRegression.getSr();
        sqE = linearRegression.getSe();
        sqT = sqE +sqR;
        sqEAverage = linearRegression.getVariance();
        sqRAverage = sqR;
        fTest = linearRegression.getF();
        rSquared = linearRegression.R2();
        r2Adjusted =
        this.r = Math.sqrt(rSquared);

        double t_obs;
        String rejection;
        t_obs = linearRegression.getTs((1+significance)/2);
        double tCritical;
        if(varTest.equalsIgnoreCase("1")){
            tCritical= linearRegression.getTa();
        }else{
            tCritical=linearRegression.getTb();
        }
        if (tCritical > t_obs){
            rejection = "Reject H0";
        }else{
            rejection = "No reject H0";
        }
        critical = linearRegression.getT0(1-confidence);


        int confidence100 = (int) (confidence *100);

        StringBuilder stringToBuild = new StringBuilder("The regression model fitted using data from the interval\n" +
                "^y = " + xa + "x +" + intercept + "\n" +
                "\n" +
                "Other statistics\n" +
                "R2 = " + rSquared + "\n" +
                "R = " + r + "\n" +
                "\n" +
                "Hypothesis tests for regression coefficients\n" +
                "HO:b=0 (a=0) H1: b<>0 (a<>0)\n" +
                "t_obs = " + t_obs + "\n" +
                "Decision: \n"+
                rejection + "\n" +
                "\n" +
                "\n" +
                "Significance model with Anova\n" +
                "H0: b=0  H1:b<>0\n" +
                "\t\t            SS\t       df\t\t     MS\t\t     F\n" +
                "Regression\t" + sqR + "\t" + "2" + "\t" + sqRAverage + "\t" + fTest + "\n" +  //simple /multi
                "Residual\t" + sqE + "\t" + (degreesOfFreedom - 1 ) + "\t " + sqEAverage + "\n" +
                "Total\t\t" + sqT + "\t" + degreesOfFreedom + "\n" +
                "\n" +
                "Decision: f  \n" +
                "0 > f0.05,(1. " + (degreesOfFreedom - 1) + ")=" + critical + "\n");
        if (fTest > critical) {
            stringToBuild.append("Reject H0\n" +                                      //reject or not
                    "The regression model is significant.\n");
        } else {
            stringToBuild.append("No reject H0\n" +
                    "The regression model is not significant.\n");
        }
        stringToBuild.append("\n" +
                "// Prediction values\n" +
                "\n" +
                "Date                Number of OBSERVED positive cases           Number of ESTIMATED/EXPECTED positive cases         \t\t" + confidence100 +"% intervals\n");
        Date dayOfTable = dateCurrentDay;
        int index;
        for (index = 1; index <= historicalPoints; index++) {
            dayOfTable = DateUtils.addDays(dayOfTable, -1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dayOfTable);
            int dayOfCalendar = calendar.get(Calendar.DAY_OF_MONTH);
            int monthOfCalendar = calendar.get(Calendar.MONTH);
            int yearOfCalendar = calendar.get(Calendar.YEAR);

            Date newDate = new Date(dayOfCalendar,monthOfCalendar,yearOfCalendar);
            String dateInString;
            if(dayOfCalendar>9){
                dateInString = ""+dayOfCalendar;
            }else{
                dateInString = "0"+dayOfCalendar;
            }
            if(monthOfCalendar>9){
                dateInString = dateInString +"/" + monthOfCalendar;
            }else{
                dateInString = dateInString + "/0"+ monthOfCalendar;
            }
            dateInString = dateInString+ "/" + yearOfCalendar;

            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            //String dayOfTableInString = simpleDateFormat.format(newDate);

            if (newDate.getDay() == 0) {
                index--;

            } else {
                double critical;
                if(independentVariable.equalsIgnoreCase("mean age")){
                    critical = linearRegression.getInterval((1+confidence)/2,testStore.getAverageAgeOfClient(dateInString));
                }else{
                    if(independentVariable.equalsIgnoreCase("total tests")){
                        critical = linearRegression.getInterval((1+confidence)/2,testStore.getNumberOfTestsPerformed(dateInString));
                    }else{
                        throw new Exception("Invalid variable");
                    }
                }
                stringToBuild.append((dateInString + "                    " + testStore.getNumberOfPositiveCasesPerDay(dateInString) + "                                      "));
                double prevision;
                if(independentVariable.equalsIgnoreCase("mean age")){
                    stringToBuild.append(linearRegression.predict(testStore.getAverageAgeOfClient(dateInString)));
                    prevision = linearRegression.predict(testStore.getAverageAgeOfClient(dateInString));
                }else{
                    if(independentVariable.equalsIgnoreCase("total tests")){
                        stringToBuild.append(linearRegression.predict(testStore.getNumberOfTestsPerformed(dateInString)));
                        prevision = linearRegression.predict(testStore.getNumberOfTestsPerformed(dateInString));
                    }else{
                        throw new Exception("Invalid variable");
                    }
                }

                stringToBuild.append( "                                      " + (prevision - critical) + "-" + (prevision + critical) + "\n");
            }
            }
        stringToReport = stringToBuild.toString();
        }

    /**
     * Getter for the string created in the constructor
     *
     * @return a string
     */
    public String getReport() {
        return stringToReport;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
