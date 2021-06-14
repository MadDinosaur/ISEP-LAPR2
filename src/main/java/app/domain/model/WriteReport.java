package app.domain.model;

import java.io.Serializable;

public final class WriteReport implements Serializable, CharSequence {

    // private File file = new File("Report.txt");
    // WriteReport str = new WriteReport();

    public WriteReport() {

        double a = 5;
        double b = 10;
        double r2 = 50;
        double r2a = 10;
        double r = 1;
        String t_obs = "observacao";
        String decision = "decisao";
        String rejection = "Reject H0/ No reject H0";
        double c = 2;
        double d = 1347.4754;
        double e = 673.7377;
        double f = 28.7538;
        double g = 15;
        double h = 351.4699;
        double i = 23.4313;
        double j = 17;
        double k = 1698.9444;
        String fi = "decisao";

        StringBuilder string = new StringBuilder("The regression model fitted using data from the interval\n" +
                "^y = " + a + "x + " + b + "\n" +
                "\n" +
                "Other statistics\n" +
                "R2 = " + r2 + "\n" +
                "R2adjusted = " + r2a + "\n" +
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
                "\t\tgrau de liberdade\tsoma dos quadrados\t\tmedia quadratica\t\testatistica de teste f\n" +
                "Regression\t" + c + "\t" + d + "\t" + e + "\t" + f + "\n" +
                "Residual\t" + g + "\t " + h + "\t " + i + "\n" +
                "Total\t\t" + j + "\t" + k + "\n" +
                "\n" +
                "Decision: " + fi + "\n" +
                "0 > f0.05,(2.15)=3.682\n" +
                "Reject H0\n" +
                "The regression model is significant.\n" +
                "\n" +
                "// Prediction values\n" +
                "\n" +
                "Date           Number of OBSERVED positive cases        Number of ESTIMATED/EXPECTED positive cases \t\t95% intervals\n" +
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