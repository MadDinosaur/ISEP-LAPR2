package app.controller;

import app.domain.model.BarcodeTextGenerator;
import app.domain.model.Company;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.store.SampleList;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static net.sourceforge.barbecue.BarcodeFactory.createUPCA;


public class SampleController {

    private Company company = App.getInstance().getCompany();
    private Sample sample;
    private SampleList sampleList = new SampleList();
    private SampleList ListOfSamples;
    private Test test;

    public List<TestDTO> getTestList() {
        TestMapper testmapper = new TestMapper(company.getTestStore().getRegisteredTests());
        return testmapper.toDtoList();
    }

    public SampleList setSampleNumber(int n) throws Exception {

        for (int i = 0; i < n; i++) {
            String barcodeText = BarcodeTextGenerator.generateBarcodeText();
            Barcode barcode = barcodeGenerator(barcodeText);

            BufferedImage barcodeJPG = generateEAN13BarcodeImage(barcodeText);
            saveBarcode(barcodeJPG, barcodeText);

            System.out.println(barcodeJPG);

            Sample sample = new Sample(barcodeText);
            sampleList.saveSample(sample);
        }

        return ListOfSamples = sampleList.getSampleList();;

    }

    public void criar(String testCode, SampleList sampleList) {
        test.setSampleList(sampleList);
    }

    public void saveBarcode(BufferedImage barcodeJPG, String barcodeText) throws IOException {
        File outputfile = new File("C:\\Users\\Windows\\Documents\\lei-21-s2-1dc-g13\\barcodes\\barcode" + barcodeText + ".jpg");
        ImageIO.write(barcodeJPG, "jpg", outputfile);
    }

    public Barcode barcodeGenerator(String barcodeText) throws BarcodeException {
        Barcode barcode = createUPCA(barcodeText);
        return barcode;
    }

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
        return BarcodeImageHandler.getImage(barcode);
    }

}
