package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Sample;

import java.util.ArrayList;

public class SampleStore {

    private ArrayList<Sample> SampleList = new ArrayList<>();

    private boolean addSample(Sample sample) {
        return this.SampleList.add(sample);
    }
    public boolean saveSample(Sample sample) { return addSample(sample); }
}
