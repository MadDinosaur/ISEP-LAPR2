package app.domain.model;

import app.domain.store.TestStore;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class WriteReport implements CharSequence {
    double rSquared;
    double r2Adjusted;
    double r;
    private double sqR, sqT, sqE;
    private double sqRAverage, sqEAverage;
    private double fTest;
    private int degreesOfFreedom;
    private String currentDay;
    double xa;
    double xb;
    double intercept;
    double critical;
    double[] x1 ;
    double[] x2 ;
    double[] y;
    String stringToReport;


    // private File file = new File("Report.txt");
    // WriteReport str = new WriteReport();


    public WriteReport(TestStore testStore, int historicalPoints, String currentDay, Date startRegression, Date finishRegression) {

        long diffInDays =  (finishRegression.getTime() - startRegression .getTime());
        int daysOfRegression = (int) TimeUnit.DAYS.convert(diffInDays, TimeUnit.MILLISECONDS);

        x1 = new double[daysOfRegression];
        x2 = new double[daysOfRegression];
        y = new double[daysOfRegression];
        String[] dateComponents = currentDay.split("/");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);
        Date dateCurrentDay = new GregorianCalendar(year, month, day).getTime();


        Date dayOfTableToMakeRegression = dateCurrentDay;
        int i;
        for (i = 0; i < daysOfRegression; i++) {
            dayOfTableToMakeRegression = DateUtils.addDays(dayOfTableToMakeRegression, -1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dayOfTableToMakeRegression);
            int dayOfCalendar = calendar.get(Calendar.DAY_OF_MONTH);
            int monthOfCalendar = calendar.get(Calendar.MONTH);
            int yearOfCalendar = calendar.get(Calendar.YEAR);
            String dateInString = dayOfCalendar + "/" + monthOfCalendar + "/" + yearOfCalendar;
            if (dayOfTableToMakeRegression.getDay() == 0) {
                i--;
                continue;
            }
            x1[i] = testStore.getNumberOfTestsPerformed(dateInString);
            x2[i] = testStore.getAverageAgeOfClient(dateInString);
            y[i] = testStore.getNumberOfPositiveCasesPerDay(dateInString);
        }

        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x1, x2, y);
        this.currentDay = currentDay;

        xa = multiLinearRegression.getX1();
        xb = multiLinearRegression.getX2();
        intercept = multiLinearRegression.getIntercept();
        degreesOfFreedom = multiLinearRegression.getDegreesOfFreedom();
        sqR = multiLinearRegression.getSqR();
        sqE = multiLinearRegression.getSqE();
        sqT = multiLinearRegression.getSqT();
        sqEAverage = multiLinearRegression.getSqEAverage();
        sqRAverage = multiLinearRegression.getSqRAverage();
        fTest = multiLinearRegression.getFTest();
        rSquared = multiLinearRegression.getRSquared();
        r2Adjusted = multiLinearRegression.getRSquaredAdjusted();
        r = multiLinearRegression.getR();
        critical = multiLinearRegression.getCritical();

        String t_obs = "observacao";
        String decision = "decisao";
        String rejection = "Reject H0/ No reject H0";


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
                "Decision: " + decision + "\n" +
                rejection + "\n" +
                "\n" +
                "\n" +
                "Significance model with Anova\n" +
                "H0: b=0  H1:b<>0\n" +
                "\t\t            df\t       SS\t\t     MS\t\t     F\n" +
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
                "Date                Number of OBSERVED positive cases           Number of ESTIMATED/EXPECTED positive cases \t\t95% intervals\n");
        Date dayOfTable = dateCurrentDay;
        int index;
        for (index = 1; index <= historicalPoints; index++) {
            dayOfTable = DateUtils.addDays(dayOfTable, -1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dayOfTable);
            int dayOfCalendar = calendar.get(Calendar.DAY_OF_MONTH);
            int monthOfCalendar = calendar.get(Calendar.MONTH);
            int yearOfCalendar = calendar.get(Calendar.YEAR);

            String dateInString = dayOfCalendar + "/" + monthOfCalendar + "/" + yearOfCalendar;
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dayOfTableInString = simpleDateFormat.format(dayOfTable);

            if (dayOfTable.getDay() == 0) {
                index--;

            } else {
                double critical = multiLinearRegression.getPrevisionforY(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString));
                stringToBuild.append((dayOfTableInString + "                    " + testStore.getNumberOfPositiveCasesPerDay(dateInString) + "                                      " + multiLinearRegression.getPrevisionforY(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString)) + "                                      " + (critical - multiLinearRegression.getCriticalValue(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString))) + "-" + (critical + multiLinearRegression.getCriticalValue(testStore.getNumberOfTestsPerformed(dateInString), testStore.getAverageAgeOfClient(dateInString)))) + "\n");
            }
        }
        stringToReport = stringToBuild.toString();
    }

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
