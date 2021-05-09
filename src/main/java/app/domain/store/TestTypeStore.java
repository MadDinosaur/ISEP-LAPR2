package app.domain.store;

import app.domain.model.CollectionMethod;
import app.domain.model.TestType;

import java.util.ArrayList;

public class TestTypeStore {

    private ArrayList<TestType> testTypeList;

    public TestTypeStore() {

    }

    public TestType createTestType() {
        return new TestType();
    }

    public CollectionMethod createCollectionMethod() {
        return new CollectionMethod();
    }

    public boolean addTestType(TestType tt) {
        if(!validateTestType(tt)) {
            return false;
        } else {
            return testTypeList.add(tt);
        }
    }
    public boolean validateTestType(TestType tt) {
        if (testTypeList.contains(tt)) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<TestType> getTestTypeList() {
        return testTypeList;
    }

}
