package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class LinearRegressionTest {
    double[] size3 = new double[3];
    double[] size4 = new double[4];

    double[] x = {1,2,3};
    double[] y = {5,3,2};

    LinearRegression linearRegression = new LinearRegression(x, y);

    @Test (expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest() {
        new LinearRegression(size3, size4);
    }

    @Test
    public void LinearRegressionTest() {
        double intercept = linearRegression.intercept();
        Assert.assertEquals(intercept, 6.3333, 5);

        double slope = linearRegression.slope();
        Assert.assertEquals(slope, -1.5, 0);

        double R2 = linearRegression.R2();
        Assert.assertEquals(R2, 0.9642, 5);

        double interceptStdErr = linearRegression.interceptStdErr();
        Assert.assertEquals(interceptStdErr, 0.6236, 5);

        double slopeStdErr = linearRegression.slopeStdErr();
        Assert.assertEquals(slopeStdErr, 0.2886, 5);

        double predict = linearRegression.predict(1.5);
        Assert.assertEquals(predict, 4.0833, 5);

        double rss = linearRegression.getSe();
        Assert.assertEquals(rss, 0.1666, 5);

        double ssr = linearRegression.getSr();
        Assert.assertEquals(ssr, 4.5, 0);

/*        double svar = linearRegression.slope();
        Assert.assertEquals(svar, 0.1666, 5);*/

        double statisticF = linearRegression.getF();
        Assert.assertEquals(statisticF, 26.999, 5);

   /*     double critical = linearRegression.getInterval(0.95);
        Assert.assertEquals(critical, 161.44, 5);*/

        String toString = linearRegression.toString();
        Assert.assertNotNull(toString);
    }
}
