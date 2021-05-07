package app.domain.store;

import app.domain.model.CollectionMethod;
import app.domain.model.TestType;

import java.util.ArrayList;

public class TestTypeStore {

    private ArrayList<TestType> testTypeList;


    public TestType createTestType() {
        return new TestType();
    }

    public CollectionMethod createCollectionMethod() {
        return new CollectionMethod();
    }

    public boolean validate(TestType tt) {
        return tt.validateAttributes();
    }

    public boolean addTestType(TestType tt) {
        if(!tt.validateAttributes() || !validateTestType(tt)) {   //ver isto crlh já me estou a passar
            return false;                                         //vdd
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
