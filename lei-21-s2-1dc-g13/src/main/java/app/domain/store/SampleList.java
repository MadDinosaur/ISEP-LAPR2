package app.domain.store;

import app.domain.model.Sample;

import java.io.Serializable;
import java.util.ArrayList;

public class SampleList implements Serializable {

    private ArrayList<Sample> sampleList = new ArrayList<>();

    /**
     * adds a sample to the sample list
     * @param sample same which will be used
     * @return success of operation
     */
    private boolean addSample(Sample sample) {
        return this.sampleList.add(sample);
    }

    /**
     * saves a sample to the sample list
     * @param sample sample which will be saved
     * @return success of operation
     */
    public boolean saveSample(Sample sample) { return addSample(sample); }

    /**
     * return list of samples
     * @return sampleList
     */
    public ArrayList<Sample> getSampleList() { return sampleList; }


    public boolean existsSample(String barcode) {
        for(Sample sample: sampleList)
            if (sample.getBarcode().equals(barcode))
                return true;

        return false;
    }

    /**
     * returns sample list size
     * @return size
     */
    public int size() { return sampleList.size(); }

}
