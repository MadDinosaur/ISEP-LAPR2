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
import java.util.ArrayList;
import java.util.List;


public class SampleController {

    private Company company = App.getInstance().getCompany();
    private Sample sample;
    private ArrayList<Sample> ListOfSamples;
    private Test test;
    private ExternalModuleBarcode adapterBarcode = new ExternalModuleBarcodeAdapter();

    public List<TestDTO> getTestList() {
        TestMapper testmapper = new TestMapper(company.getTestStore().getRegisteredTests());
        return testmapper.toDtoList();
    }

    public void setTestCode(String testCode) { test = company.getTestStore().getTestByCode(testCode); }

    public SampleList setSampleNumber(int n) throws Exception {

        SampleList sampleList = test.getSampleList();

        for (int i = 0; i < n; i++) {
            String barcodeText = sample.generateBarcodeUCP();
            BufferedImage barcode = adapterBarcode.barcodeGenerator(barcodeText);

            adapterBarcode.saveBarcode(barcode, barcodeText);

            Sample sample = new Sample(barcodeText);
            sampleList.saveSample(sample);
        }

       return sampleList;
    }

    public void createSampleList(int n) throws Exception {
        test.setSampleList(setSampleNumber(n));
        System.out.println("Successfully created " + n + " sample(s).");
    }

}
