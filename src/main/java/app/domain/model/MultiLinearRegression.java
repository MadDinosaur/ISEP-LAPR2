package app.domain.model;

public class MultiLinearRegression {
    private double[][] x;
    private double[][] xTranspose;
    private double[][] xTransposeX;
    private double[][] xTransposeXInversed;
    private double[][] xTransposeY;

    public MultiLinearRegression(double[] x1,double[] x2, double[] y) {
        if (x1.length != y.length && x2.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }

        x = new double[x1.length][3];

        for(int index = 0; index< x1.length;index++){
            x[index][0] = 1;
            x[index][1] = x1[index];
            x[index][2] = x2[index];
        }
        double[][] yVertical = new double[y.length][1];
        for(int i = 0;i<yVertical.length;i++) {
            yVertical[i][0] = y[i];
        }
        xTranspose = transposeMatrix(x);
        xTransposeX = matrixMultiplication(xTranspose,x);
        xTransposeY = matrixMultiplication(xTranspose,yVertical);

    }

    private double[][] transposeMatrix(double[][] x){
        int column = 3;
        int row = x.length;
        double[][] transpose = new double[column][row];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                transpose[j][i] = x[i][j];
            }
        }
        return transpose;
    }
    public static double[][] matrixMultiplication(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] product = new double[firstMatrix.length][secondMatrix[0].length];
        for(int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < secondMatrix[0].length; j++) {
                for (int k = 0; k < firstMatrix[0].length; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return product;
    }
}
