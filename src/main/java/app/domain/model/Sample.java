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
<<<<<<< HEAD
    public String generateBarcodeUPC() {
        Random r = new Random();
        long barcode = 10000000000L + (long) (r.nextDouble() * 89999999999L);
=======
    public static String generateBarcodeUCP() {
        Random random = new Random();
        long barcode = 10000000000L + (long) (random.nextDouble() * 89999999999L);
>>>>>>> 73c27c0d7f98990806a8fd200c3deb395b7f1576
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
