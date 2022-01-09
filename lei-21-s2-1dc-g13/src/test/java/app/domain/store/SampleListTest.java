package app.domain.store;

import app.domain.model.Sample;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class SampleListTest {
    SampleList sampleList = new SampleList();
    Sample sample1 = new Sample("123456789012");
    Sample sample2 = new Sample("111111111111");
    Sample sample3 = new Sample("123123123123");

    @Test
    public void getSampleListTest() {
        sampleList.saveSample(sample1);
        sampleList.saveSample(sample2);
        sampleList.saveSample(sample3);
        ArrayList<Sample> list = sampleList.getSampleList();
        ArrayList<Sample> actualList = new ArrayList<>();
        actualList.add(sample1);
        actualList.add(sample2);
        actualList.add(sample3);
        Assert.assertEquals(list, actualList);
    }

    @Test
    public void listSizeTest() {
        sampleList.saveSample(sample1);
        sampleList.saveSample(sample2);
        sampleList.saveSample(sample3);
        int size = sampleList.size();
        int actualSize = 3;
        Assert.assertEquals(size, actualSize);
    }

    @Test
    public void existsSampleTest() {
        sampleList.saveSample(sample1);
        sampleList.saveSample(sample2);
        sampleList.saveSample(sample3);
        boolean result = sampleList.existsSample("123456789012");
        Assert.assertTrue(result);
    }
}
