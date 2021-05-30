package app.controller;

import app.domain.adapter.ExternalModuleBarcode;
import app.domain.adapter.ExternalModuleBarcodeAdapter;
import app.domain.model.Company;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.store.SampleList;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class SampleController {

    private Company company = App.getInstance().getCompany();
    private Sample sample;
    private ArrayList<Sample> ListOfSamples;
    private Test test;
    private ExternalModuleBarcode adapterBarcode = new ExternalModuleBarcodeAdapter();

    /**
     * goes to TestStore and gets a list of all tests ready for sample collection
     * then it creates a DTO from that same list and sends it to the UI
     * @return TestList
     */
    public List<TestDTO> getTestList() {
        TestMapper testmapper = new TestMapper(company.getTestStore().getRegisteredTests());
        return testmapper.toDtoList();
    }

    /**
     * selects a specific test from the test store by using its code
     * @param testCode Code of the selected test
     */
    public void setTestCode(String testCode) { test = company.getTestStore().getTestByCode(testCode); }

    /**
     * creates n samples and n barcodes and saves them as .jpg files
     * @param n number of samples requested by the medical lab technician
     * @return sampleList
     * @throws Exception
     */
    public SampleList setSampleNumber(int n) throws Exception {

        SampleList sampleList = test.getSampleList();

        File theDir = new File("barcodes");
        if (!theDir.exists()){ theDir.mkdirs(); }

        for (int i = 0; i < n; i++) {
            String barcodeText = sample.generateBarcodeUPC();
            BufferedImage barcode = adapterBarcode.barcodeGenerator(barcodeText);

            adapterBarcode.saveBarcode(barcode, barcodeText);

            Sample sample = new Sample(barcodeText);
            sampleList.saveSample(sample);
        }

       return sampleList;
    }

    /**
     * associates the list of samples to the selected test
     * @param n number of samples
     * @throws Exception
     */
    public void createSampleList(int n) throws Exception {
        test.setSampleList(setSampleNumber(n));
        System.out.println("Successfully created " + n + " sample(s).");
    }

}
