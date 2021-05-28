package app.controller;

import app.domain.model.BarcodeTextGenerator;
import app.domain.model.Company;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.store.TestStore;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import java.awt.image.BufferedImage;
import java.util.List;
import static net.sourceforge.barbecue.BarcodeFactory.createUPCA;

public class SampleController {

    private Company company;
    private Sample sample;
    public List<Test> getTestList() { return company.getUnusedTests(); }

    public void setTestCode(Test testCode) {

    }

    public void setSampleNumber(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            String barcodeText = BarcodeTextGenerator.generateBarcodeText();
            String barcode = barcodeGenerator(barcodeText);
            BufferedImage barcodeJPEG = generateEAN13BarcodeImage(barcode);

            Sample sample = new Sample(barcodeText);
        }
    }

    public String barcodeGenerator(String barcodeText) throws BarcodeException {
        Barcode barcode = createUPCA(barcodeText);
        return "123123";
    }

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
        return BarcodeImageHandler.getImage(barcode);
    }

}
