package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

/**
 *  The code LinearRegression class performs a simple linear regression
 *  on an set of n data points (y_i, x_i).
 *  That is, it fits a straight line y = alpha + beta * x,
 *  (where y is the response variable, x is the predictor variable,
 *  alpha is the y-intercept, and beta is the slope)
 *  that minimizes the sum of squared residuals of the linear regression model.
 *  It also computes associated statistics, including the coefficient of
 *  determination R^2 and the standard deviation of the
 *  estimates for the slope and y-intercept.
 *
 */

public class LinearRegression {
    private final double intercept, slope;
    private final double r2;
    private final double svar0, svar1;
    double ssr = 0.0;      // regression sum of squares //sqr
    double rss = 0.0;      // residual sum of squares   //sqe
    double tss;                                         //sqt
    double svar;                                        //mqr
    private double sxx, syy, sxy, meanX, meanY, variance, sr, se, student;
    private int n, numTests;
    double statisticF;
    private double[] x;
    private double[] y;
    private int degreesOfFreedom;

    /**
     * Performs a linear regression on the data points (y[i], x[i]).
     *
     * @param  x the values of the predictor variable
     * @param  y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public LinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        this.x = x;
        this.y = y;
        this.n = x.length;

        // first pass
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
        for (int i = 0; i < n; i++) {
            sumx += x[i];
            sumx2 += x[i] * x[i];
            sumy += y[i];
        }

        double xbar = sumx / n;
        double ybar = sumy / n;
        this.meanX = xbar;
        this.meanY = ybar;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }

        this.sxx = xxbar;
        this.syy = yybar;
        this.sxy = xybar;

        slope = xybar / xxbar;
        intercept = ybar - slope * xbar;

        // more statistical analysis
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            double fit = slope * x[i] + intercept;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }

        this.se = rss;
        this.sr = ssr;

        degreesOfFreedom = n - 2;
        this.numTests = degreesOfFreedom;
        r2 = ssr / yybar;
        double svar = rss / degreesOfFreedom; //variancia
        this.variance = svar;
        svar1 = svar / xxbar;
        svar0 = svar / n + xbar * xbar * svar1;
    }

    /**
     * Returns the y-intercept alpha of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the y-intercept alpha of the best-fit line y = alpha + beta * x
     */
    public double intercept() {
        return intercept;
    }

    /**
     * Returns the slope beta of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the slope beta of the best-fit line y = alpha + beta * x
     */
    public double slope() {
        return slope;
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2,
     * which is a real number between 0 and 1
     */
    public double R2() {
        return r2;
    }

    public double getMeanY() {
        return meanY;
    }

    /**
     * Returns the standard error of the estimate for the intercept.
     *
     * @return the standard error of the estimate for the intercept
     */
    public double interceptStdErr() {
        return Math.sqrt(svar0);
    }

    /**
     * Returns the standard error of the estimate for the slope.
     *
     * @return the standard error of the estimate for the slope
     */
    public double slopeStdErr() {
        return Math.sqrt(svar1);
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     * variable x
     */
    public double predict(double x) {
        return slope * x + intercept;
    }

    public double getInterval(double confidence, double day) {
        double student = getTs(1 - confidence);
        return student * Math.sqrt(variance) * Math.sqrt(1 + 1 / (double) x.length + (Math.pow(day - meanX, 2)) / sxx);
    }

    public double getTs(double significance) { //para H0
        TDistribution tDistribution = new TDistribution(numTests);
        return tDistribution.inverseCumulativeProbability(1-((significance)/2));
    }

    public double getT0(double significance) {
        FDistribution fDistribution = new FDistribution(1,numTests);
        return fDistribution.inverseCumulativeProbability(1-significance);
    }

    public double getTa() { //ver se é maior que ts, se for rejeita h0
        double sd = Math.sqrt(variance);
        return intercept / (sd * Math.sqrt(1 / (double) x.length + meanX * meanX / sxx));
    }

    public double getTb() {
        double sd = Math.sqrt(variance);
        return slope / (sd * Math.sqrt(1 / (double) x.length + meanX * meanX / sxx));
    }

    public double getSe() {
        return se;
    } // 2º SS

    public int getDegreesOfFreedom() {
        return degreesOfFreedom;
    }

    public double getSr() {
        return sr;
    } // SS e MS - regression

    public int getNumTests() {
        return numTests;
    } // 2º df

    public double getVariance() {
        return variance;
    } //2ºMS

    public double getF() {
        return getSr() / variance;
    }

}