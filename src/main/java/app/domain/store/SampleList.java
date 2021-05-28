package app.domain.store;

import app.domain.model.Sample;

import java.util.List;

public class SampleList {
    List<Sample> samples;

    public boolean existsSample(String barcode) {
        for(Sample sample: samples)
            if (sample.getBarcode().equals(barcode))
                return true;

        return false;
    }
}
