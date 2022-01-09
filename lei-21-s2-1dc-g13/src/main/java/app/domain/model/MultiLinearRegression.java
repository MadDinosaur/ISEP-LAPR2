package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public class MultiLinearRegression {

    /**
     *  Array of values of the multiplication of the matrix X-Transposed with the matrix X-Inverted
     */
    private final double[][] xTransposeXInversed;

    /**
     *  Array of values of the multiplication of the matrix Y-Transposed with the matrix Y
     */
    private double yTransposeY;

    /**
     *  Array of values of the intercept and regressors of Regression
     */
    private final double[][] vector;

    /**
     * Number of degrees of freedom of Regression
     */
    int degreesOfFreedom;

    /**
     * Number of values in the array y
     */
    int numberOfObservations;


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


    private final double critical;

    /**
     * Statistic value for R^2
     */
    double rSquared;

    /**
     * Statistic value for R^2 Adjusted
     */
    double rSquaredAdjusted;

    /**
     * Statistic value for R
     */
    double r;

    double confidence;



    /**
     * Write Report class constructor for Multiple Linear Regressions
     *
     * @param x1          First array of values for the regression
     * @param x2          Second array of values for the regression
     * @param y           Array of values of the dependent variable for the regression
     */
    public MultiLinearRegression(double[] x1, double[] x2, double[] y) {


        if (x1.length != y.length && x2.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        numberOfObservations = y.length;

        double[][] x = new double[x1.length][3];
        for (int index = 0; index < x1.length; index++) {
            x[index][0] = 1;
            x[index][1] = x1[index];
            x[index][2] = x2[index];
        }
        double[][] xTranspose = transposeMatrix(x);
        double[][] xTransposeX = matrixMultiplication(xTranspose, x);
        xTransposeXInversed = invert(xTransposeX);


        double[][] yVertical = new double[y.length][1];
        for (int i = 0; i < yVertical.length; i++) {
            yVertical[i][0] = y[i];
        }

        double[][] xTransposeY = matrixMultiplication(xTranspose, yVertical);

        vector = matrixMultiplication(xTransposeXInversed, xTransposeY);
        intercept = vector[0][0];
        this.xa = vector[1][0];
        this.xb = vector[2][0];

        for (double yNumber: y
             ) {
            yTransposeY += Math.pow(yNumber,2);
        }
        double sumY=0;
        for (double yNumber: y
        ) {
            sumY += yNumber;
        }
        sumY = Math.pow(sumY,2);
        double sumYAverage = sumY / y.length;
        double total = 0;
        for (int i = 0;i<3;i++){
            total += vector[i][0] * xTransposeY[i][0];
        }
        sqT= yTransposeY - sumYAverage;
        sqE = yTransposeY - total;
        sqR = sqT - sqE;
        degreesOfFreedom= y.length - 1;
        sqRAverage = sqR/ 2;
        sqEAverage = sqE/ (degreesOfFreedom-2);
        fTest = sqRAverage / sqEAverage;
        TDistribution fDistribution = new TDistribution(degreesOfFreedom-2);
        critical = fDistribution.inverseCumulativeProbability((1+confidence)/2);
        rSquared = sqR/sqT;
        double lengthArray = y.length;
        rSquaredAdjusted = 1- (lengthArray -1)/(lengthArray-3)*(1-rSquared);
        r= Math.sqrt(rSquared);
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    /**
     * Getter for the degrees of freedom
     *
     * @return number of degrees of freedom
     */
    public int getDegreesOfFreedom(){ return degreesOfFreedom;}

    /**
     * Getter for the vector
     *
     * @return vector with the intercept and regressor
     */
    public double[][] getVector() {
        return vector;
    }

    /**
     * Getter for the total square sum of Regression
     *
     * @return total square sum of Regression
     */
    public double getSqT() {
        return sqT;
    }

    /**
     * Getter for the square sum of Regression Error
     *
     * @return square sum of Regression Error
     */
    public double getSqE() {
        return sqE;
    }

    /**
     * Getter for the square sum of Regression
     *
     * @return square sum of Regression
     */
    public double getSqR() {
        return sqR;
    }

    /**
     * Getter for the average square sum of Regression
     *
     * @return average square sum of Regression
     */
    public double getSqRAverage() {
        return sqRAverage;
    }

    /**
     * Getter for the average square sum of Regression Error
     *
     * @return average square sum of Regression Error
     */
    public double getSqEAverage() {
        return sqEAverage;
    }

    /**
     * Getter for the statistic value of F
     *
     * @return value of F
     */
    public double getFTest() {
        return fTest;
    }

    public double getCritical() {
        return critical;
    }

    /**
     * Getter for the statistic value of R^2
     *
     * @return value of R^2
     */
    public double getRSquared() {
        return rSquared;
    }

    /**
     * Getter for the statistic value of R^2 Adjusted
     *
     * @return value of R^2 Adjusted
     */
    public double getRSquaredAdjusted() {
        return rSquaredAdjusted;
    }

    /**
     * Getter for the statistic value of R
     *
     * @return value of R
     */
    public double getR() {
        return r;
    }

    /**
     * Getter for value of the first regressor
     *
     * @return value of the first regressor
     */
    public double getX1() {
        return xa;
    }

    /**
     * Getter for value of the second regressor
     *
     * @return value of the second regressor
     */
    public double getX2() {
        return xb;
    }

    /**
     * Getter for value of the intercept
     *
     * @return value of the intercept
     */
    public double getIntercept() {
        return intercept;
    }

    /**
     * Getter for the prevision of y in the regression
     *
     * @param a1    value of x1
     * @param a2    value of x2
     * @return prevision of y value
     */
    public double getPrevisionforY(double a1, double a2){
        return a1*xa + a2*xb + intercept;
    }

    /**
     * Getter for the hypothesis test with -first regressor
     *
     * @return the confidence value for the first regressor
     */
    public double getConfidenceRegressionB1(double significance){
        TDistribution tDistribution = new TDistribution(degreesOfFreedom-2);
        return tDistribution.inverseCumulativeProbability(significance)*Math.sqrt(sqEAverage*xTransposeXInversed[1][1]);
    }

    /**
     * Getter for the hypothesis test with -second regressor
     *
     * @return the confidence value for the second regressor
     */
    public double getConfidenceRegressionB2(double significance){
        TDistribution tDistribution = new TDistribution(degreesOfFreedom-2);
        return tDistribution.inverseCumulativeProbability(significance)*Math.sqrt(sqEAverage*xTransposeXInversed[2][2]);
    }

    public double getConfidenceRegressionIntercept(double significance){
        TDistribution tDistribution = new TDistribution(degreesOfFreedom-2);
        return tDistribution.inverseCumulativeProbability(significance)*Math.sqrt(sqEAverage*xTransposeXInversed[0][0]);
    }

    /**
     * Getter for the hypothesis test with -intercept
     *
     * @return the confidence value for the intercept
     */
    public double xTransposeX1(){
        return xTransposeXInversed[1][1];
    }
    public double xTransposeX2(){
        return xTransposeXInversed[2][2];
    }
    public double xTransposeX0(){
        return xTransposeXInversed[0][0];
    }

    /**
     * Getter for the critical value
     *
     * @return value of the intercept
     */
    public double getCriticalValue(double a1, double a2){
        double[][] aValuesFirst = new double[3][3];
        aValuesFirst[0][0] = 1;
        aValuesFirst[0][1] = a1;
        aValuesFirst[0][2] = a2;
        double[][] aValues = new double[3][3];
        aValues[0][0] = 1;
        aValues[1][0] = a1;
        aValues[2][0] = a2;
        double[][] criticalValues = matrixMultiplication(aValuesFirst,xTransposeXInversed);
        TDistribution tDistribution = new TDistribution(degreesOfFreedom-2);
        double tStrudent = tDistribution.inverseCumulativeProbability(0.975);
        criticalValues = matrixMultiplication(criticalValues,aValues);
        return Math.sqrt(criticalValues[0][0]*sqEAverage)*tStrudent;
    }

    /**
     * Method that transposes a matrix
     *
     * @param x  Matrix to be transposed
     * @return Transposed Matrix
     */
    private double[][] transposeMatrix(double[][] x) {
        int column = 3;
        int row = x.length;
        double[][] transpose = new double[column][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                transpose[j][i] = x[i][j];
            }
        }
        return transpose;
    }

    /**
     * Method that multiplies 2 matrix
     *
     * @param firstMatrix  Matrix to be multiplied
     * @param secondMatrix  Second matrix to be multiplied
     * @return Multiplied Matrix
     */
    private static double[][] matrixMultiplication(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] product = new double[firstMatrix.length][secondMatrix[0].length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < secondMatrix[0].length; j++) {
                for (int k = 0; k < firstMatrix[0].length; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return product;
    }

    /**
     * Method that inverts a matrix
     *
     * @param a Matrix to be multiplied
     * @return inverted matrix
     */
    private double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;


        gaussian(a, index);


        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];


        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }

        return x;
    }


    public void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;


        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }


        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }


            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];


                a[index[i]][j] = pj;


                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

}


