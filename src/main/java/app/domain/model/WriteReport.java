package app.domain.model;

import java.io.Serializable;
import java.io.Writer;
import java.util.Date;

public class WriteReport implements CharSequence {
    double a1;
    double a2;
    double rSquared;
    double r2Adjusted;
    double r;
    private double sqR, sqT, sqE;
    private double sqRAverage, sqEAverage;
    private double fTest;
    private int degreesOfFreedom;
    private int numberOfObservations;
    private int currentDay;
    double[] x1;
    double[] x2;



    // private File file = new File("Report.txt");
    // WriteReport str = new WriteReport();
    public WriteReport(MultiLinearRegression multiLinearRegression){

    }

    public WriteReport() {

        double a = 5;
        double b = 10;

        double r = 1;
        String t_obs = "observacao";
        String decision = "decisao";
        String rejection = "Reject H0/ No reject H0";
        String fi = "decisao";

        StringBuilder stringToBuild = new StringBuilder("The regression model fitted using data from the interval\n" +
                "^y = " + a + "x + " + b + "\n" +
                "\n" +
                "Other statistics\n" +
                "R2 = " + rSquared+ "\n" +
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
                "Residual\t" +   sqE + "\t " + (degreesOfFreedom-2) + "\t "+ sqEAverage + "\n" +
                "Total\t\t" +    sqT + "\t" + degreesOfFreedom + "\n" +
                "\n" +
                "Decision: f  \n" +
                "0 > f0.05,(2. "+(degreesOfFreedom-2)+")=3.682\n" +  //simple /multi
                "Reject H0\n" +                                      //reject or not
                "The regression model is significant.\n" +
                "\n" +
                "// Prediction values\n" +
                "\n" +
                "Date           Number of OBSERVED positive cases        Number of ESTIMATED/EXPECTED positive cases \t\t95% intervals\n");
                for(double x:x1){
                    stringToBuild.append(currentDay+ "                    "+currentDay+"  "+ x1[0]+"                                      ");
                }
                "29/05/2021                    21                                      22.32\t\t\t\t\t                13.16-23.48\n" +
                "28/05/2021                    20                                      21.32\t\t\t\t                \t19.16-23.48\n" +
                "27/05/2021                    14                                      14.33\t\t\t\t                \t12.17-16.49\n" +
                "26/05/2021                    23                                      22.35\t\t\t\t                \t20.19-24.51\n" +
                "25/05/2021                    12                                      11.47\t\t\t                \t\t 9.31-13.63\n" +
                "24/05/2021                    22                                      23.01\t\t\t                \t\t20.85-25.17\n" +
                "22/05/2021                    20                                      22.02\t\t\t                \t\t19.86-24.18\n" +
                "21/05/2021                    20                                      22.02\t\t\t                \t\t19.86-24.18\n" +
                "20/05/2021                    14                                      13.97\t\t\t                \t\t11.81-16.13\n" +
                "19/05/2021                    23                                      21.95             \t\t\t\t\t19.79-24.11\n" +
                "18/05/2021                    12                                      11.98\t                \t\t\t\t 9.82-14.14\n" +
                "17/05/2021                    22                                      23.01\t                \t\t\t\t20.85-25.17\n" +
                "15/05/2021                    20                                      21.02\t                \t\t\t\t18.86-23.18\n" +
                "14/05/2021                    20                                      21.02\t                \t\t\t\t18.86-23.18\n" +
                "13/05/2021                    14                                      15.03\t                \t\t\t\t12.87-17.19");

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
