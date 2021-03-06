package app.domain.model;

import java.util.Random;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class Sample {

    private String barcodeText;
    private final Random r = new Random();

    /**
     * sets barcode text
     * @param barcodeText sample barcode
     */
    public Sample (String barcodeText) {
        this.barcodeText = barcodeText;
    }

    public Sample() {

    }

    /**
     * generator a 12 digit number which will be used to create a barcode
     * @return barcode
     */

    public String generateBarcodeUPC() {
        long barcode = 10000000000L + (long) (r.nextDouble() * 89999999999L);
        return String.valueOf(barcode);
    }

    /**
     * Sample's barcode text getter
     * @return barcodeText
     */
    public String getBarcode() {
        return barcodeText;
    }
}
