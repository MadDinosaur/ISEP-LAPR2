package app.domain.model;

import java.util.Random;

public class BarcodeTextGenerator {

    public static String generateBarcodeText(){

        Random r = new Random();
        long barcode = 10000000000L + (long) (r.nextDouble() * 89999999999L);

        return String.valueOf(barcode);

    }
}
