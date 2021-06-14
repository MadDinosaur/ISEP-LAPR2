package app.domain.model;

public class MultiLinearRegression {
    private double[][] x;
    private double[][] xTranspose;
    private double[][] xTransposeX;
    private double[][] xTransposeXInversed;
    private double[][] xTransposeY;
    private double[][] vector;

    public MultiLinearRegression(double[] x1, double[] x2, double[] y) {
        if (x1.length != y.length && x2.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }

        x = new double[x1.length][3];

        for (int index = 0; index < x1.length; index++) {
            x[index][0] = 1;
            x[index][1] = x1[index];
            x[index][2] = x2[index];
        }
        double[][] yVertical = new double[y.length][1];
        for (int i = 0; i < yVertical.length; i++) {
            yVertical[i][0] = y[i];
        }
        xTranspose = transposeMatrix(x);
        xTransposeX = matrixMultiplication(xTranspose, x);
        xTransposeXInversed = invert(xTransposeX);
        xTransposeY = matrixMultiplication(xTranspose, yVertical);
        vector = matrixMultiplication(xTransposeXInversed,xTransposeY);
        System.out.println(vector[0][0]);
        System.out.println(vector[1][0]);
        System.out.println(vector[2][0]);

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


