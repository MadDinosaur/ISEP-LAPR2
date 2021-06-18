package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public class MultiLinearRegression {
    private final double[][] xTransposeXInversed;
    private double yTransposeY;
    private final double[][] vector;
    int degreesOfFreedom;
    int numberOfObservations;
    private final double x1, x2, intercept;
    private final double sqR, sqT, sqE;
    private final double sqRAverage, sqEAverage;
    private final double fTest;
    private final double critical;
    private final double rSquared, rSquaredAdjusted, r;


    public MultiLinearRegression(double[] x1, double[] x2, double[] y) {

        double[][] x = new double[x1.length][3];
        double[][] xTranspose = transposeMatrix(x);
        double[][] xTransposeX = matrixMultiplication(xTranspose, x);
        xTransposeXInversed = invert(xTransposeX);

        if (x1.length != y.length && x2.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        numberOfObservations = y.length;

        for (int index = 0; index < x1.length; index++) {
            x[index][0] = 1;
            x[index][1] = x1[index];
            x[index][2] = x2[index];
        }
        double[][] yVertical = new double[y.length][1];
        for (int i = 0; i < yVertical.length; i++) {
            yVertical[i][0] = y[i];
        }

        double[][] xTransposeY = matrixMultiplication(xTranspose, yVertical);

        vector = matrixMultiplication(xTransposeXInversed, xTransposeY);
        intercept = vector[0][0];
        this.x1 = vector[1][0];
        this.x2 = vector[2][0];
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
        FDistribution fDistribution = new FDistribution(2, y.length - 3);
        critical = fDistribution.inverseCumulativeProbability(1-0.05);
        rSquared = sqR/sqT;
        double lengthArray = y.length;
        rSquaredAdjusted = 1- (lengthArray -1)/(lengthArray-3)*(1-rSquared);
        r= Math.sqrt(rSquared);
    }

    public int getDegreesOfFreedom(){ return degreesOfFreedom;}
    public double[][] getVector() {
        return vector;
    }
    public double getSqT() {
        return sqT;
    }
    public double getSqE() {
        return sqE;
    }
    public double getSqR() {
        return sqR;
    }
    public double getSqRAverage() {
        return sqRAverage;
    }
    public double getSqEAverage() {
        return sqEAverage;
    }
    public double getFTest() {
        return fTest;
    }
    public double getCritical() {
        return critical;
    }
    public double getRSquared() {
        return rSquared;
    }
    public double getRSquaredAdjusted() {
        return rSquaredAdjusted;
    }
    public double getR() {
        return r;
    }
    public double getX1() {
        return x1;
    }
    public double getX2() {
        return x2;
    }
    public double getIntercept() {
        return intercept;
    }
    public double getPrevisionforY(double a1, double a2){
        return a1*x1 + a2*x2 + intercept;
    }

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

    public static double[][] matrixMultiplication(double[][] firstMatrix, double[][] secondMatrix) {
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


    public double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
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

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    public void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
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

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

}


