package app.domain.store;

import app.domain.model.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleList {

    private ArrayList<Sample> SampleList = new ArrayList<>();

    private boolean addSample(Sample sample) {
        return this.SampleList.add(sample);
    }

    public boolean saveSample(Sample sample) { return addSample(sample); }

    public ArrayList<Sample> getSampleList() { return SampleList; }

    List<Sample> samples;

    public boolean existsSample(int barcode) {
        for(Sample sample: samples)
            if (sample.getBarcode() == barcode)
                return true;

        return false;
    }
}
