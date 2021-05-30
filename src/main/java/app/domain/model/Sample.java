package app.domain.model;

import java.util.Random;

public class Sample {

    private String barcodeText;

    public Sample (String barcodeText) {
        this.barcodeText = barcodeText;
    }

    public static String generateBarcodeUCP() {
        Random r = new Random();
        long barcode = 10000000000L + (long) (r.nextDouble() * 89999999999L);
        return String.valueOf(barcode);
    }

    public String getBarcode() {
        return barcodeText;
    }

}
