package app.domain.model;

import junit.framework.TestCase;

import java.util.Arrays;

public class MultiLinearRegressionTest extends TestCase {

    public void testInvert() {
        double[][] a = new double[3][3];
        double[] aa = new double[3];
        double[] ab = new double[3];
        double[] ac = new double[3];
        a[0][0] = 12;
        a[0][1] = 1100;
        a[0][2] = 123;
        a[1][0] = 1100;
        a[1][1] = 101370;
        a[1][2] = 11308;
        a[2][0] = 123;
        a[2][1] = 11308;
        a[2][2] = 1293;
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(aa,ab,ac);
        double[][] b;
        b = multiLinearRegression.invert(a);
        System.out.println(Arrays.deepToString(b));
        //por acabar
    }
}