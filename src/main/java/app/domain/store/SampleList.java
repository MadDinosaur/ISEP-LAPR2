package app.domain.store;

import app.domain.model.Sample;

import java.util.List;

public class SampleList {
    List<Sample> samples;

    public boolean existsSample(int barcode) {
        for(Sample sample: samples)
            if (sample.getBarcode() == barcode)
                return true;

        return false;
    }
}
