package app.domain.store;

import app.domain.model.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleList {

    private ArrayList<Sample> sampleList = new ArrayList<>();

    private boolean addSample(Sample sample) {
        return this.sampleList.add(sample);
    }

    public boolean saveSample(Sample sample) { return addSample(sample); }

    public ArrayList<Sample> getSampleList() { return sampleList; }

    public boolean existsSample(String barcode) {
        for(Sample sample: sampleList)
            if (sample.getBarcode().equals(barcode))
                return true;

        return false;
    }
    public int size() {
        return sampleList.size();
    }
}
