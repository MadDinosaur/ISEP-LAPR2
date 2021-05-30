package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class SampleTest {
    @Test
    public void testBarcodeText() {
        String barcodeText = "123456789012";
        Sample sample = new Sample(barcodeText);
        String barcode = sample.getBarcode();

        Assert.assertEquals(barcode, "123456789012");
    }

    @Test
    public void barcodeTextLength() {
        Sample sample = new Sample();
        String barcodeText = sample.generateBarcodeUPC();
        int length = barcodeText.length();
        Assert.assertEquals(length, 11);
    }
}
