package app.domain.model;

import java.util.Random;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class Sample {

    private String barcodeText;


    /**
     * sets barcode text
     * @param barcodeText
     */
    public Sample (String barcodeText) {
        this.barcodeText = barcodeText;
    }

    /**
     * generator a 12 digit number which will be used to create a barcode
     * @return barcode
     */
    public static String generateBarcodeUCP() {
        Random random = new Random();
        long barcode = 10000000000L + (long) (random.nextDouble() * 89999999999L);
        return String.valueOf(barcode);
    }

    /**
     * Sample's barcode text getter
     * @return barcodeText
     */
    public String getBarcode() {
        return barcodeText;
    }

    @Override
    public String toString() {
        return barcodeText;
    }
}
