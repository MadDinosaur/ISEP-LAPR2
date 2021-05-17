package app.domain.store;

import app.domain.model.CollectionMethod;
import app.domain.model.Exceptions.InvalidTestType;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @author 1200985 Tomás Cancela
 */
public class TestTypeStore {

    /**
     * List of test types
     */
    private ArrayList<TestType> testTypeList = new ArrayList<>(Collections.singleton(new TestType("Blood")));

    /**
     * Empty constructor
     */
    public TestTypeStore() {

    }

    /**
     * Constructs a Test Type Store with a test type list
     * @param testTypeList List of test types
     */
    public TestTypeStore(ArrayList<TestType> testTypeList) {
        this.testTypeList = testTypeList;
    }

    /**
     *
     * @return Creates a new test type
     */
    public TestType createTestType() {
        return new TestType();
    }

    /**
     *
     * @return Creates a new collection method
     */
    public CollectionMethod createCollectionMethod() {
        return new CollectionMethod();
    }

    /**
     *
     * @param testType A Test Type
     * @return Adds a test type to the test type list
     */
    public boolean addTestType(TestType testType) {
        if(!validateTestType(testType)) {
            throw new InvalidTestType();
        } else {
            return testTypeList.add(testType);
        }
    }

    /**
     *
     * @param testType Test Type
     * @return True if the test type list doesn't contain the respective test type and false otherwise
     */
    public boolean validateTestType(TestType testType) {
        if (testTypeList.contains(testType)) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Test Type List getter
     * @return Test Type List
     */
    public ArrayList<TestType> getTestTypeList() {
        return testTypeList;
    }

}
