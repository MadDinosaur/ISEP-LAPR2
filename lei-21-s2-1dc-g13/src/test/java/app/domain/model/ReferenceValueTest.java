package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ReferenceValueTest {
    double minValue = 123.0;
    double maxValue = 123123.0;
    String metric = "metric";
    ReferenceValue referenceValue = new ReferenceValue(minValue, maxValue, metric);

    @Test
    public void getTest() {
        double minValue1 = referenceValue.getMinValue();
        double maxValue1 = referenceValue.getMaxValue();
        String metric1 = referenceValue.getMetric();

        Assert.assertEquals(minValue, minValue1, 0);
        Assert.assertEquals(maxValue, maxValue1, 0);
        Assert.assertEquals(metric, metric1);

    }

}
